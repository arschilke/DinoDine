package mad.dinodine;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    Button bookNow, viewBooked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getSupportActionBar().setDisplayShowTitleEnabled(false); Hides the DinoDine title, but still has green bar at top. start with empty or fullscreen activity?
        setContentView(R.layout.home_activity);

        bookNow = findViewById(R.id.bookTable);
        viewBooked = findViewById(R.id.viewBookings);

        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create_Booking = new Intent(getApplicationContext(), PeopleSelectActivity.class);
                startActivity(create_Booking);
            }
        });

        viewBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to table/list view screen
                Intent lookup_Bookings = new Intent(HomeActivity.this, TableViewListViewActivity.class);
                startActivity(lookup_Bookings);

            }
        });
        AppRoomDB mDb;
        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());
        if(mDb.tableModel().getAllTables().isEmpty())
            populateWithJSON(mDb);
        if(mDb.bookingModel().getAllBookings().isEmpty())
            loadTestData(mDb);
    }
    public void loadTestData(AppRoomDB mDb){

        String[] first = {"Kate", "Joe", "Emma", "Jordan", "Casey", "Alex", "Stephen"};
        String[] last = {"McGregor", "Williams", "Smith", "Cooper", "Jones", "Miller"};
        int records = 30;
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < records; i++) {
            Guest g = new Guest(first[i % first.length], last[i % last.length], "2345 9032", first[i%7] + "." + last[i%5] + "@email.com");
            Time ctime = new Time(c.getTimeInMillis());
            Booking b = new Booking((i % 10) + 1, c.getTimeInMillis(), ctime, ctime, g.getGuestID());
            int tempid = (i%10)+1;
            String id = (tempid<10)?("0"+tempid):""+tempid;
            String tid = "T" + id;
            Allocation a = new Allocation(b.getBookingID(), tid);
            mDb.guestModel().insertGuest(g);
            mDb.bookingModel().insertBooking(b);
            Log.d("guest:",g.toString());
            Log.d("booking:",b.toString());
            Log.d("table:",tid);
            Log.d("allocation:",a.toString());
            mDb.allocationModel().insertAllocation(a);
        }
    }
    protected void populateWithJSON(final AppRoomDB db){
        // db.tableModel().deleteAll();
        ArrayList<Table> file = loadDiningTables("");

        //if(db.tableModel().getAllTables()==null)
        for(Table a : file) {
            db.tableModel().insertTable(a);
        }
    }
    ArrayList<Table> loadDiningTables(String filename){
        //get assets folder.. and set up streams to read it in.
        AssetManager am = getAssets();
        InputStream is = null;
        //String filename = "tables.json";
        if(filename.isEmpty()){filename= "tables.json";}

        String result = null;
        JSONObject jObject = null;
        ArrayList<Table> resultTables = new ArrayList<Table>(); //array we will return.
        //reading in json file
        try {
            is = am.open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Couldn't read from file", (int) 1).show();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception squish) {
            }
        }

        try {
            jObject = new JSONObject(result);
            JSONArray tables = jObject.getJSONArray("tables");
            for(int i=0; i < tables.length(); i++) {
                try{
                    JSONObject temp = tables.getJSONObject(i);
                    //insert statement... for RoomDB
                    String id = temp.getString("id");
                    int capacity = temp.getInt("capacity");
                    int maxCapacity = temp.getInt("maxCapacity");
                    JSONArray neighbours = temp.getJSONArray("canJoin");
                    ArrayList<String> n = new ArrayList<String>();
                    for(int j = 0; j < neighbours.length(); j++) {
                        n.add(neighbours.get(j).toString());
                    }
                    Table a = new Table(id,capacity,maxCapacity,n);
                    resultTables.add(a);
                } catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage(), (int) 1).show();}
            }
        }
        catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage(), (int) 1).show();}
        return resultTables;
    }
}
