package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


/*
****
****
****  Use date pickers instead of a button interface?
****
 */

public class TimeSelectActivity extends AppCompatActivity {
    Button start[], finish[];
    TextView DateTV;
    ImageButton submitBtn;
    Booking booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);

        //retrieve booking object.
        Intent intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");

        //Toast toast=Toast.makeText(getApplicationContext(),"dateString: "+booking.getDateString(),(int)1);


        DateTV = findViewById(R.id.DateText);
        DateTV.setText(booking.getDateString());
        //initialise arrays.
        start = new Button[9];
        finish = new Button[3];
        //Intent intent = getIntent();
        //bookingNow = (Booking) intent.getSerializableExtra("bookingNow");

        //TextView DateTV = findViewById(R.id.DateText);
        //DateTV.setText(bookingNow.getDateString());

        //array for start btns
        for(int x = 0; x < 9; x++){
            String btnID = "btn" + (x + 1);
            int idNum = getResources().getIdentifier(btnID, "id", getPackageName());
            start[x] = findViewById(idNum);

            //Set time as text value for buttons.
            start[x].setText(booking.getTime());
            //TODO make a time function or equivalent that goes to nearest half hour then add's 30 mins to each sequential button
            //Will probably end up using pickers instead of buttons, just better from usabilty aspect, probably easier to code.

            start[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO change background to signal selected and unselect others
                    //
                    //TODO save value to variable

                }
            });
        }
        //array for finish btns
        for(int x = 0; x < 3; x++){
            String btnID = "FBtn" + (x + 1);
            int idNum = getResources().getIdentifier(btnID, "id", getPackageName());
            finish[x] = findViewById(idNum);
            //TODO look up values for each btn; on click listener for each btn
            finish[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO auto select start + default setting
                    //TODO change background to signal selected and unselect others
                    //TODO save value to variable (ask how storing in DB)

                }
            });
        }

        submitBtn = findViewById(R.id.submitTimeBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //todo get selected btns value, convert to Date OBJ then setDate in Booking


            }
        });
    }
}
