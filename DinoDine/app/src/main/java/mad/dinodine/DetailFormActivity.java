package mad.dinodine;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DetailFormActivity extends AppCompatActivity {
    private AppRoomDB mDb;

    Spinner article, DietRestrict;
    EditText fName, lName, phoneNum, emailET;
    Button submitBtn;
    Intent intent;
    Booking booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form);

        //getDB and populate with table data from JSON file.
        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());
        populateWithJSON(mDb);
        intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");

       // article = findViewById(R.id.article);
       // DietRestrict = findViewById(R.id.dietRestrict);

       // phoneExt = findViewById(R.id.phoneExt);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        phoneNum = findViewById(R.id.phone);
        emailET = findViewById(R.id.email);

        submitBtn = findViewById(R.id.submitBtn);


        //TODO need to sent data to match with previous pages
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating Guest, need to add to Booking as well
                String f = fName.getText().toString();
                String l = lName.getText().toString();
                String p = phoneNum.getText().toString();
                String e = emailET.getText().toString();

                Guest PersonInfo = null;
                Table t = mDb.tableModel().getTable("T01");
                try {
                    PersonInfo = new Guest(f, l, p, e);

                    //booking = (Booking) intent.getSerializableExtra("bookingNow");
                    booking.setGuest(PersonInfo.getGuestID());
                    Allocation a = new Allocation(booking.getBookingID(),t.getTableID());

                    //insert guest and booking to db.
                    mDb.guestModel().insertGuest(PersonInfo);
                    mDb.bookingModel().insertBooking(booking);
                    mDb.allocationModel().insertAllocation(a);
                    //Allocate Table


                    Toast.makeText(getApplicationContext(),"Booking successful!",(int) 1).show();
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                catch (NullPointerException npe){
                    Toast.makeText(DetailFormActivity.this, "Both Names are Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }

    protected void populateWithJSON(@NonNull final AppRoomDB db){
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
