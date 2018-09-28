<<<<<<< HEAD
package mad.dinodine;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity{

    ListView listView;

    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(Bundle savedInstanceState);
        setContentView(R.layout.list_view);

        listView = (ListView) findViewById(R.id.listView);

        items = new ArrayList<>();
        items.add("Table 1");
        items.add("Table 2");
        items.add("Table 3");
        items.add("Table 4");
        items.add("Table 5");
        items.add("Table 6");
        items.add("Table 7");
        items.add("Table 8");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = listView.getItemAtPosition(i).toString();
                Toast.makeText(ListViewActivity.this, "" + text, Toast.LENGTH_SHORT.show());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<String> templist = new ArrayList<String>();

                for(String temp: items){
                    if(temp.toLowerCase().contains(newText.toLowerCase))){
            templist.add(temp);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(listView.this,
                        android.R.layout.simple_list_item_1, templist);
                listView.setAdapter(adapter);

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /*@Override
            public boolean onQueryTextChange(String s) {
                return false;
            }*/
        });
        return super.onCreateOptionsMenu(menu);
    }
}
=======
package mad.dinodine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ListViewActivity extends AppCompatActivity {

    EditText searchBox = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        searchBox = findViewById(R.id.search);

        //Below three lines just prevent focus going to search box in order to stop keyboard popping up.
        findViewById(R.id.textView5).setFocusable(true);
        findViewById(R.id.textView5).setFocusableInTouchMode(true);
        findViewById(R.id.textView5).requestFocus();


        searchBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            }
        });
    }
}
>>>>>>> 3d0250562d67ff813d5f5c13a648f7528fff3147
