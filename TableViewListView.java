package com.example.a18925907.dinodine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TableViewListView extends AppCompatActivity {

    Button listViewBtn, tableViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view_list_view);

        listViewBtn = findViewById(R.id.listView);
        tableViewBtn = findViewById(R.id.tableView);

        listViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to List View screen
            }
        });

        tableViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to Table View screen
            }
        });
    }
}