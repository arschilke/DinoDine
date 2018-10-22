package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.sql.Time;
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
    ImageButton refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_select);
        refresh = findViewById(R.id.refresh);


        mDb = AppRoomDB.getInMemoryDatabase(getApplicationContext());

        for (int x = 0; x<numberOfTables; x++){
            String tableID = "T";
            int buttonID = -1;
            if((x+1) < 10) {tableID += "0" + (x+1);}
            else{tableID += (x+1);}
            buttonID = getResources().getIdentifier(tableID,"id", getPackageName());

            tables[x] = findViewById(buttonID);

            tables[x].setText(tableID.substring(1));

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
        bookingsNow();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingsNow();
            }
        });
    }
    public void bookingsNow(){
        Time current = new Time(Calendar.getInstance().getTimeInMillis());
        Date currentDate = Calendar.getInstance().getTime();
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);

        alist =  mDb.allocationModel().getAllAllocations();
        tlist = new ArrayList<String>();

        for(Allocation a : alist){
            Booking b = mDb.bookingModel().getBooking(a.getBooking());
            Date bDate = b.getDate();
            bDate.setHours(0);
            bDate.setMinutes(0);
            bDate.setSeconds(0);

            //if(current is after start >= 0 && current is before end && date is the same){
            if(compareTimes(current, b.getStartTime()) >= 0 && compareTimes(current, b.getEndTime()) <= 0 && currentDate.toString().compareTo(bDate.toString()) == 0){
                //Log.d("myActivity","TRUE");
                tlist.add(a.getTable_id());
            }
        }
        for(int x = 0; x < tables.length; x++){
            String tableID = "T";
            if((x+1) < 10) {tableID += "0" + (x+1);}
            else{tableID += (x+1);}

            if (tlist.size() > 0) {
                for (String nowID : tlist) {
                    if (nowID.equals(tableID)) {
                        tables[x].setBackground(this.getResources().getDrawable(R.drawable.rounded_allocated_border, getTheme()));
                    }
                }
            }
        }
    }
    public int compareTimes(Time aTime,Time bTime){
       String aStr = aTime.toString();
       String bStr = bTime.toString();
        //array = {hour, min, sec}
       int aTimeArray[] = {Integer.parseInt(aStr.substring(0,2)), Integer.parseInt(aStr.substring(3,5)), Integer.parseInt(aStr.substring(6))};
       int bTimeArray[] = {Integer.parseInt(bStr.substring(0,2)), Integer.parseInt(bStr.substring(3,5)), Integer.parseInt(bStr.substring(6))};
       for (int x = 0; x < aTimeArray.length; x++){
           if(aTimeArray[x] > bTimeArray[x] ){
               return 1;
           }
           else if (aTimeArray[x] < bTimeArray[x]) {
               return -1;
           }
       }
        return 0;
    }
}