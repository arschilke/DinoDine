package mad.dinodine;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;


public class BookingViewActivity extends AppCompatActivity {
    private AppRoomDB mDb;
    int tableID=-1;
    String tableName="";
    TextView title = null;
    ArrayList<Booking> blist;
    ListView list[] = new ListView[7];
    ImageButton deleteButton;
    LinearLayout ll;

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

        for(int i = 0;i < 7; i++) {
            String l = "listView"+i;
            list[i] = findViewById(getResources().getIdentifier(l, "id", getPackageName()));
        }

        ll = findViewById(R.id.ll);

        deleteButton = findViewById(R.id.delete);
        populateListViews();


    }
    public void populateListViews(){
        Context context = getApplicationContext();
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<String> time = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> first = new ArrayList<String>();
        ArrayList<String> last = new ArrayList<String>();
        ArrayList<String> num = new ArrayList<String>();
        ArrayList<String> email = new ArrayList<String>();
        ArrayList<String> phone = new ArrayList<String>();


        blist = (ArrayList<Booking>) mDb.bookingModel().getBookingsForTable(tableName);
        Collections.sort(blist);
        for (int i = 0; i < blist.size(); i++){
            Booking b = blist.get(i);
            //Booking b = mDb.bookingModel().getBooking(alist.get(i).getBooking());
            Guest g = mDb.guestModel().getGuestByID(b.getGuest());

            time.add(df.format(b.getStartTime()));
            date.add(df2.format(b.getDate()));
            first.add(g.getFirstName());
            last.add(g.getLastName());
            num.add("" + b.getNumOfPeople());
            email.add(g.getEmail());
            phone.add(g.getPhoneNum());
            ImageButton d = new ImageButton(getApplicationContext());
            d.setBackground(getResources().getDrawable(R.drawable.ic_delete_black_24dp,getTheme()));
            ll.addView(d, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

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
                date
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