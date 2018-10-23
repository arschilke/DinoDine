package mad.dinodine;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class BookingViewActivity extends AppCompatActivity {
    private AppRoomDB mDb;
    int tableID=-1;
    String tableName="";
    TextView title = null;
    ArrayList<Booking> blist;
    ArrayList<String> outputStrings;
    ArrayAdapter<String> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_view);

        Intent intent = getIntent();
        tableID = intent.getIntExtra("tableID", 0);
        tableName = intent.getStringExtra("tableName");

        title = findViewById(R.id.bookViewTitle);
        //title.setText("tableName: " + tableName + " tableID: " +tableID);
        title.setText("Viewing booking for table " + tableName.substring(1));

        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());

        blist = (ArrayList<Booking>) mDb.bookingModel().getBookingsForTable(tableName);
        outputStrings = new ArrayList<>();

        list = findViewById(R.id.listView);
        Context context = getApplicationContext();
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");



        for (int i = 0; i < blist.size(); i++){
            Booking b =  blist.get(i);
            Guest g = mDb.guestModel().getGuestByID(b.getGuest());

            outputStrings.add(df.format(b.getStartTime()) + " \t" +
                                        g.getFirstName() + " \t" +
                                        g.getLastName() + " \t" +
                                        b.getNumOfPeople() + " \t\t" +
                                        g.getEmail()  + " \t" +
                                        g.getPhoneNum());
        }
        adapter = new ArrayAdapter<>(
                BookingViewActivity.this,
                android.R.layout.simple_list_item_1,
                outputStrings
        );
        list.setAdapter(adapter);
    }
}