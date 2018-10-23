package mad.dinodine;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BookingViewActivity extends AppCompatActivity {
    private AppRoomDB mDb;
    int tableID=-1;
    String tableName="";
    TextView title = null;
    List<Allocation> alist;
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

        alist = mDb.allocationModel().getAllocationsForTable(tableName);




    }
}