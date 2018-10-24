package mad.dinodine;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
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
        //if(mDb.tableModel().getAllTables().isEmpty()){
        //populateWithJSON(mDb);}
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


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating Guest, need to add to Booking as well
                String f = fName.getText().toString();
                String l = lName.getText().toString();
                String p = phoneNum.getText().toString();
                String e = emailET.getText().toString();

                boolean valid = true;
                String msg = "";
                if(f.trim().equals("") && l.trim().equals("")){ //if name fields are empty.
                    valid = false;
                    msg += "Must enter a value for first name or last name\n";
                }

                Guest PersonInfo = null;
                Table t = mDb.tableModel().getTable("T01");//todo A function that searches through tables and selects next available one
                                                            //write a query? Or does one exist already..
                PersonInfo = new Guest(f, l, p, e);

                booking.setGuest(PersonInfo.getGuestID());
                Allocation a = null;
                if(t!=null) {
                    a = new Allocation(booking.getBookingID(), t.getTableID());
                }
                else {
                    valid = false;
                    msg += "Couldn't get data for table\n";
                }

                if(valid) { //if everything is ok up to this point, then insert data to database.
                    mDb.guestModel().insertGuest(PersonInfo);
                    mDb.bookingModel().insertBooking(booking);
                    mDb.allocationModel().insertAllocation(a);

                    Toast.makeText(getApplicationContext(),"Booking successful!",(int) 1).show();
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

                //sends email to guest with booking details
                    if (v.getId() == R.id.submitBtn) {
                        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.parse("mailto:?subject=" + "Dinodine - Your booking details!" + "&body=" + "Hi there, \n\nYour booking details are as follows: \n\n Name: " + fName.getText().toString() + " " + lName.getText().toString() + "\nDate: " + booking.getDate() + "\nTime: " + booking.getTime() + "\nNumber of people: " +  booking.getNumOfPeople() + "\n\n Looking forward to seeing you! \n\nDinodine Team" + "&to=" + emailET.getText().toString());
                        mailIntent.setData(data);
                        startActivity(Intent.createChooser(mailIntent, "Send Email"));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),msg,(int) 0).show();
                }
            }
        });

    }
}
