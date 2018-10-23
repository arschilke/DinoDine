package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {


    private AppRoomDB mDb;

    Button z = null;

    LinearLayout ll = null;
    SearchView searchV = null;
    ListView resultL = null;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());

        searchV = findViewById(R.id.search);
        resultL = findViewById(R.id.results);
        ll = findViewById(R.id.list_view_ll); //to test adding bookings dynamically.

        z = findViewById(R.id.cb);
        z.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Test.class);
                startActivity(intent);
            }

        });



        ArrayList<String> arrayBookings = new ArrayList<>();
        arrayBookings.addAll(Arrays.asList(getResources().getStringArray(R.array.bookings)));

        ArrayList<String> ba = new ArrayList<String>();
        //Add first row - contains column headers.
        String[] info = {"Date", "Time", "Table", "People", "Name"};
        String infoFormat = "%1$-22s  %2$-15s   %3$s     %4$s   %5$-30.30s";
        ba.add(String.format(infoFormat, (Object[])info));

        List<Booking> bl = mDb.bookingModel().getAllBookings();

        for(Booking b : bl){
            Guest g = mDb.guestModel().getGuestByID(b.getGuest());
            String date = b.getDateString();
            String sTime = b.getStartTime().toString().substring(0,5);
            String eTime = b.getEndTime().toString().substring(0,5);
            String time = sTime+" - "+eTime;
            String fname = g.getFirstName();
            String lname = g.getLastName();
            String name = fname+" "+lname;
            int numPpl = b.getNumOfPeople();
            String tableID = mDb.tableModel().getTableFromBookingID(b.getBookingID()).getTableID();
            String t = tableID.substring(1);

            String fmt = "";
            String format = "%-15s  %-15s   %s     %2d           %-30.30s";
            //%[argument_index$][flags][width][.precision]conversion
            fmt = String.format(format, date,time, t,numPpl, name);

            ba.add(fmt);
        }



        //ba.add(fmt);

//        String da,ti,na; int nu;
//
//        da="23/10/2018";ti="17:00 - 19:00";na="Amy Smith";nu=5;if(nu<10){na=" "+na;}
//        fmt = String.format(format, da,ti, t,nu,na);
//        //ba.add(fmt);
//
//        da="24/11/2018";ti="18:00 - 19:30";na="John Fogerty and now time for a really long one. And maybe I'll keep increasing and see what happens...";nu=10;if(nu<10){na=" "+na;}
//        fmt = String.format(format, da,ti, t,nu,na);
//        //ba.add(fmt);
//
//        da="28/11/1990";ti="18:30 - 21:00";na="Daniel Masterton";nu=8;if(nu<10){na=" "+na;}
//        fmt = String.format(format, da,ti,t,nu,na);
        //ba.add(fmt);

//        23/10/2018  |  17:00-19:00  |  Amy Smith                   |  5
//        24/11/2018  |  18:00-19:30  |  John Fogerty                |  4
//        28/11/1990  |  18:30-21:00  |  Daniel Masterton            |  8

        adapter = new ArrayAdapter<>(
                ListViewActivity.this,
                android.R.layout.simple_list_item_1,
                ba
        );

        resultL.setAdapter(adapter);

        searchV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bookings, menu);
        MenuItem item = menu.findItem(R.id.results);
        //SearchView searchView = (SearchView)item.getActionView();

/*        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }*/
        return super.onCreateOptionsMenu(menu);
    }


}
