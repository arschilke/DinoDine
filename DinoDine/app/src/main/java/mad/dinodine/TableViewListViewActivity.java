package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TableViewListViewActivity extends AppCompatActivity {

    Button listViewBtn, tableViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view_list_view);

        listViewBtn = findViewById(R.id.listViewBtn);
        tableViewBtn = findViewById(R.id.tableViewBtn);

        listViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent create_Booking = new Intent(TableViewListViewActivity.this, ListViewActivity.class);
                startActivity(create_Booking);
            }
        });

        tableViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to Table View screen
                Intent create_Booking = new Intent(TableViewListViewActivity.this, TableSelectActivity.class);
                startActivity(create_Booking);
            }
        });
    }
}