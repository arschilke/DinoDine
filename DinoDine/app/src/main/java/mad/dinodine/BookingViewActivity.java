package mad.dinodine;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    TableLayout tableLayout;
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
        tableLayout = findViewById(R.id.table_layout);

        Context context = getApplicationContext();
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");



        for (int i = 0; i < blist.size(); i++){
            Booking b =  blist.get(i);
            Guest g = mDb.guestModel().getGuestByID(b.getGuest());
            TableRow tableRow = new TableRow(context);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(layoutParams);

            TextView textView = new TextView(context);
            textView.setText(df.format(b.getStartTime()));

            tableRow.addView(textView, 0);

            TextView textView1 = new TextView(context);
            textView.setText(g.getFirstName());
            tableRow.addView(textView1, 1);

            TextView textView2 = new TextView(context);
            textView.setText(g.getLastName());
            tableRow.addView(textView2, 2);

            TextView textView3 = new TextView(context);
            textView.setText("" + b.getNumOfPeople());
            tableRow.addView(textView3, 3);

            TextView textView4 = new TextView(context);
            textView.setText(g.getEmail());
            tableRow.addView(textView4, 4);

            TextView textView5 = new TextView(context);
            textView.setText(g.getPhoneNum());
            tableRow.addView(textView5, 5);

            tableLayout.addView(tableRow);
        }

    }
}