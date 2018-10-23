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
    ArrayList<Allocation> alist;
    ListView list[] = new ListView[6];

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

        alist = (ArrayList<Allocation>) mDb.allocationModel().getAllocationsForTable(tableName);

        for(int i = 0;i < 6; i++) {
            String l = "listView"+i;
            list[i] = findViewById(getResources().getIdentifier(l, "id", getPackageName()));
        }
        Context context = getApplicationContext();
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");

        ArrayList<String> time = new ArrayList<String>();
        ArrayList<String> first = new ArrayList<String>();
        ArrayList<String> last = new ArrayList<String>();
        ArrayList<String> num = new ArrayList<String>();
        ArrayList<String> email = new ArrayList<String>();
        ArrayList<String> phone = new ArrayList<String>();



        for (int i = 0; i < alist.size(); i++){

            Booking b = mDb.bookingModel().getBooking(alist.get(i).getBooking());
            Guest g = mDb.guestModel().getGuestByID(b.getGuest());

            time.add(df.format(b.getStartTime()));
            first.add(g.getFirstName());
            last.add(g.getLastName());
            num.add("" + b.getNumOfPeople());
            email.add(g.getEmail());
            phone.add(g.getPhoneNum());

        }

        int i = 0;
        list[i].setAdapter(new ArrayAdapter<String>(
                    BookingViewActivity.this,
                    android.R.layout.simple_list_item_1,
                    time
            ));
            i++;

        list[i].setAdapter(new ArrayAdapter<String>(
                BookingViewActivity.this,
                android.R.layout.simple_list_item_1,
                first
        ));

        i++;

        list[i].setAdapter(new ArrayAdapter<>(
                BookingViewActivity.this,
                android.R.layout.simple_list_item_1,
                last
        ));
        i++;

        list[i].setAdapter(new ArrayAdapter<>(
                BookingViewActivity.this,
                android.R.layout.simple_list_item_1,
                num
        ));
        i++;

        list[i].setAdapter(new ArrayAdapter<>(
                BookingViewActivity.this,
                android.R.layout.simple_list_item_1,
                email
        ));
        i++;

        list[i].setAdapter(new ArrayAdapter<>(
                BookingViewActivity.this,
                android.R.layout.simple_list_item_1,
                phone
        ));

    }
}