package mad.dinodine;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amitshekhar.DebugDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

public class Test extends AppCompatActivity{

    private AppRoomDB mDb;
    private TextView tab = null;
    private LinearLayout ll = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        tab = findViewById(R.id.tabText);
        ll = findViewById(R.id.ll);
        Button btn = findViewById(R.id.load);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //display bookings..
                List<Booking> bookingList= mDb.bookingModel().getAllBookings();

                for(Booking b : bookingList) {
                    TextView tmp = new TextView(getApplicationContext());
                    tmp.setText(b.toString());
                    tmp.setTextColor(Color.BLACK);
                    ll.addView(tmp,0);
                }

                //display all guests
                List<Guest> gl = mDb.guestModel().getAllGuests();
                for(Guest g: gl)
                {
                    TextView tmp = new TextView(getApplicationContext());
                    tmp.setTextColor(Color.BLACK);
                    tmp.setText(g.getFirstName() + " " + g.getLastName());
                    ll.addView(tmp,0);
                }
            }
        });
        /*btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView tmp = new TextView(getApplicationContext());
                TextView tmp1 = new TextView(getApplicationContext());
                TextView tmp2 = new TextView(getApplicationContext());
                TextView tmp3 = new TextView(getApplicationContext());


                Table t = new Table();//tableID randomly generated UUID (string)
                    Log.d("Table",t.toString());

                Guest g = new Guest();//guestID autoGenerate from ROOM. (int)
                    Log.d("Guest",g.toString());

                Booking b = new Booking(); //booking setGuest(int)
                    b.setGuest(g.getGuestID());
                    Log.d("Booking", b.toString());

                Allocation a = new Allocation(b.getBookingID(),t.getTableID());//allocation booking(int),table(String)
                    Log.d("Allocation",a.toString());

                mDb.tableModel().insertTable(t);
                tmp.setText(t.toString());
                tmp.setTextColor(Color.BLACK);

                mDb.guestModel().insertGuest(g);
                tmp1.setText(g.toString());
                tmp1.setTextColor(Color.BLACK);

                mDb.bookingModel().insertBooking(b);
                tmp2.setText(b.toString());
                tmp2.setTextColor(Color.BLACK);

                mDb.allocationModel().insertAllocation(a);
                tmp3.setText(a.toString());
                tmp3.setTextColor(Color.BLACK);

                ll.addView(tmp,0);
                ll.addView(tmp1,1);
                ll.addView(tmp2,2);
                ll.addView(tmp3,3);
                TextView be = new TextView(getApplicationContext());
                be.setText("\n-----------------------------------------\n");
                be.setTextColor(Color.BLACK);
                ll.addView(be,4);
            }
        });*/

        tab = findViewById(R.id.tabText);
        //tab2 = findViewById(R.id.loadedTables);
        //ll = findViewById(R.id.ll);

        //ArrayList<Table> diningTables = loadDiningTables("");

/*        tab.setText("Retrieved from JSON file\n");tab.append("Retrieved from DB");
        for(Table a : diningTables) {
            tab.append(a.toString());
            Toast.makeText(getApplicationContext(),a.toString(),(int) 0).show();
        }*/

        //String x = AppRoomDB.getInMemoryDatabase(getApplicationContext()).getOpenHelper().getDatabaseName();
        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());
        //mDb.guestModel().deleteAll(); //will give errors if there are bookings existing.. change constraint to cascade? so everything related is deleted? delete guest= delete booking.
        Calendar c  = Calendar.getInstance(); //current moment/date
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY,17); time.set(Calendar.MINUTE,0); time.set(Calendar.SECOND,0);
        Time s = new Time(time.getTimeInMillis());//17:00?
        time.set(Calendar.HOUR_OF_DAY,19); time.set(Calendar.MINUTE,0); time.set(Calendar.SECOND,0);
        Time e = new Time(time.getTimeInMillis());//19:00?
        Log.d("SQL_TIME:",s.toString()+ " - " +s.getTime());
        Log.d("SQL_TIME:",e.toString()+ " - " +e.getTime());


        if(mDb.guestModel().getGuestByID("G02")!=null){
            mDb.allocationModel().deleteAllocation("B02","T02");
            mDb.bookingModel().deleteBooking("B02");
            mDb.guestModel().deleteGuest("G02");

            mDb.allocationModel().deleteAllocation("B01","T02");
            mDb.bookingModel().deleteBooking("B01");
            mDb.guestModel().deleteGuest("G01");
            mDb.tableModel().deleteTable("T02");
        }
        //mDb.guestModel().deleteGuest("G01");
        //mDb.guestModel().deleteGuest("G02");
        if(mDb.tableModel().getTable("T02")==null)
            mDb.tableModel().insertTable(new Table("T02", 5, 8));

        mDb.guestModel().insertGuest( new Guest("G01","Amy","Smith","0000","at@email.com"));
        mDb.bookingModel().insertBooking(new Booking("B01", 5, c.getTimeInMillis(), s,e,"G01"));
        mDb.allocationModel().insertAllocation(new Allocation("B01","T02"));

        mDb.guestModel().insertGuest(new Guest("G02","Fred", "Jones", "0000","fj@email.com"));
        mDb.bookingModel().insertBooking(new Booking("B02", 4, c.getTimeInMillis(), s, e, "G02"));
        mDb.allocationModel().insertAllocation(new Allocation("B02","T02"));


        //delete allocation first. shouldn't touch table or booking..
        //delete booking.. leaves guest alone..
        //delete guest..

        //tables should never be deleted

        String x = mDb.getName();
        String y = mDb.toString();
        //Toast.makeText(getApplicationContext(),x + "\n" + y,(int) 1).show();

        //populateWithJSON(mDb);
        List<Table> tb = fetchData();
        tab.append("Starting DB Load...");
        for(Table t : tb) {
            //create textview
            //TextView temp = new TextView(getApplicationContext());
            TextView temp2 = new TextView(getApplicationContext());

            //temp.setText("Added from DATABASE");
            //temp.append("FINISH-APPENDING-FOR-TV1");

            temp2.setText("idx: "+ ll.getChildCount()+"\n");
            temp2.append(t.toString());
            //ll.addView(temp);
            temp2.append("\nAdded table");
            temp2.setTextColor(Color.BLACK);
            ll.addView(temp2);



            TextView toto = new TextView(getApplicationContext());
            toto.setText(x);
            toto.setTextColor(Color.BLACK);
            //ll.addView(toto);
        }
        //String x = AppRoomDB.getInMemoryDatabase(getApplicationContext()).getOpenHelper().getDatabaseName();
        //Toast.makeText(getApplicationContext(),x,(int) 1).show();
        //String backupDBPath = AppRoomDB.getDatabase(context).getOpenHelper().getWritableDatabase().getPath();
    }//endOnCreate


    public static void setInMemoryRoomDatabases(SupportSQLiteDatabase... database) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Class[] argTypes = new Class[]{HashMap.class};
                HashMap<String, SupportSQLiteDatabase> inMemoryDatabases = new HashMap<>();
                // set your inMemory databases
                inMemoryDatabases.put("InMemoryOne.db", database[0]);
                inMemoryDatabases.put("", database[1]);
                Method setRoomInMemoryDatabase = debugDB.getMethod("setInMemoryRoomDatabases", argTypes);
                setRoomInMemoryDatabase.invoke(null, inMemoryDatabases);
            } catch (Exception ignore) {

            }
        }
    }

    private List<Table> fetchData(){
        //kind of logic shouldn't be in an activity. (So where do I put it?)
        return mDb.tableModel().getAllTables();
    }

    /*protected void populateWithJSON(@NonNull final AppRoomDB db){
        db.tableModel().deleteAll();
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
            tab.setText("");
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
    }*/
}
