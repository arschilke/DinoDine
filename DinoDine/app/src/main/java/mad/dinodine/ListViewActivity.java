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
        List<Booking> bl = mDb.bookingModel().getAllBookings();

        for(Booking b : bl){
            ba.add(b.getBookingID() +" : " + b.getDateString());
        }

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
