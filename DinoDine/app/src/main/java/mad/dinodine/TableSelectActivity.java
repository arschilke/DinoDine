package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TableSelectActivity extends AppCompatActivity {
    private AppRoomDB mDb;
    static final int numberOfTables = 10;
    Button tables[] = new Button[numberOfTables];
    List<Allocation> alist;
    ArrayList<String> tlist; //list of tables currently in progress
    Date current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_select);

        current = Calendar.getInstance().getTime();
// use allocation and java to search for date

        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());
        alist =  mDb.allocationModel().getAllAllocations();

        for(Allocation a : alist){
           Booking b = mDb.bookingModel().getBooking(a.getBooking());
           if(b.getStartTime().before(current) && b.getEndTime().after(current)){
               tlist.add(a.getTable_id());
           }
        }

        for (int x = 0; x<numberOfTables; x++){
            String tableID = "T";
            int buttonID = -1;
            if((x+1) < 10) {tableID += "0" + (x+1);}
            else{tableID += (x+1);}
            buttonID = getResources().getIdentifier(tableID,"id", getPackageName());

            tables[x] = findViewById(buttonID);

            tables[x].setText(tableID.substring(1));

            if(tlist.size() > 0) {
                for (String nowID : tlist) {
                    if (nowID.equals(tableID)) {
                        tables[x].setBackground(this.getResources().getDrawable(R.drawable.rounded_allocated_border, getTheme()));
                    }
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