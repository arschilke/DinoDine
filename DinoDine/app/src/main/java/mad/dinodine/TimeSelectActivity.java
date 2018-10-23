package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.HOUR_OF_DAY;

public class TimeSelectActivity extends AppCompatActivity {

    TextView instr = null;
    ImageButton submit = null;
    TimePicker start, finish;
    final int DEFAULT_LENGTH = 1; //the default length of a reservation

    Booking booking = null;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);

        Intent intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");

        instr = findViewById(R.id.instructions);
        submit = findViewById(R.id.submitTimeBtn);
        start = findViewById(R.id.StartWidget);
        finish = findViewById(R.id.FinishWidget);

        instr.setText("Select times for " + booking.getDateString());
        //start.setText(booking.getTime());
        //finish.setText(booking.getTime());

        int h = Integer.parseInt(booking.getTime().substring(0,2));
        int m = Integer.parseInt(booking.getTime().substring(3));

        if((m-30) < 0) { m=30; } else { m = 0; h += 1; } //round min to nearest half hour

        start.setHour(h);
        start.setMinute(m);


        finish.setMinute(m);
        finish.setHour(h+DEFAULT_LENGTH);

        start.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                finish.setHour(hourOfDay+DEFAULT_LENGTH);
                finish.setMinute(minute);
            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DetailFormActivity.class);
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, start.getHour());c.set(Calendar.MINUTE, start.getMinute());c.set(Calendar.SECOND, 0);
                //Log.d("time:",""+start.getHour()+ " " + start.getMinute());
                booking.setStartTime(new Time(c.getTimeInMillis()));

                c.set(Calendar.HOUR_OF_DAY, finish.getHour());c.set(Calendar.MINUTE, finish.getMinute());c.set(Calendar.SECOND, 0);
                //Log.d("time:",""+finish.getHour()+ " " + finish.getMinute());
                booking.setEndTime(new Time(c.getTimeInMillis()));

                boolean valid = true;
                String msg = "";

                if(booking.getStartTime().getTime() > booking.getEndTime().getTime()){ //check times are valid; // check startTime isn't before currentTime if bookingdate == todays date..
                    valid = false;
                    msg += "End time must be after Start time\n";
                }

                if(valid) {
                    intent.putExtra("booking", booking);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),msg,(int) 0).show();
                }
            }
        });
    }
}