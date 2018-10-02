package mad.dinodine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;


import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends AppCompatActivity {

    ListView search_bookings;
    ArrayAdapter<String> adapter;

   //Commented out because revamping... EditText searchBox = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        search_bookings =  (ListView) findViewById(R.id.search);

        ArrayList<String> arrayBookings = new ArrayList<>();
        arrayBookings.addAll(Arrays.asList(getResources().getStringArray(R.array.bookings)));

        adapter = new ArrayAdapter<>(
                ListViewActivity.this,
                android.R.layout.simple_list_item_1,
                arrayBookings
        );

        search_bookings.setAdapter(adapter);

        //Below three lines just prevent focus going to search box in order to stop keyboard popping up.
        /**findViewById(R.id.textView5).setFocusable(true);
        findViewById(R.id.textView5).setFocusableInTouchMode(true);
        findViewById(R.id.textView5).requestFocus();


        /**searchBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            }
        });**/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bookings, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        return super.onCreateOptionsMenu(menu);

    }


}
