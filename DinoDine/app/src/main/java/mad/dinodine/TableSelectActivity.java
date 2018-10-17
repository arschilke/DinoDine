package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class TableSelectActivity extends AppCompatActivity {
    private AppRoomDB mDb;
    static final int numberOfTables = 10;
    Button tables[] = new Button[numberOfTables];
    List<Booking> bookingsOnNow;
    List<String> coloredTableIDs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_select);



        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());
        bookingsOnNow = mDb.bookingModel().getBookingsOnNow();
        for(Booking b: bookingsOnNow){
            List<Allocation> alist=  mDb.allocationModel().getAllAllocations(b.getBookingID());
            for(Allocation a: alist){
                coloredTableIDs.add(a.getTable());
            }
        }

        for (int x = 0; x<numberOfTables; x++){
            String tableID = "t";
            int buttonID = -1;
            if((x+1) < 10) {tableID += "0" + (x+1);}
            else{tableID += (x+1);}
            buttonID = getResources().getIdentifier(tableID,"id", getPackageName());

            tables[x] = findViewById(buttonID);

            tables[x].setText(tableID.substring(1));
            for(String nowID : coloredTableIDs){
                if (nowID.equals(tableID)) {
                    tables[x].setBackground(this.getResources().getDrawable(R.drawable.rounded_allocated_border,getTheme()));
                }
            }

            final String tbl = tableID;
            final int j = x;
            tables[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(),BookingViewActivity.class);
                    intent.putExtra("tableID",tables[j].getId());
                    intent.putExtra("tableName",tbl);
                    startActivity(intent);
                }

            });
        }
    }
}