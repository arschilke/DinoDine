package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

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
                try{ booking.setStartTime(new Time(start.getHour(),start.getMinute(),0));} //change obj to simple int?
                catch( NullPointerException npe){
                    Toast.makeText(TimeSelectActivity.this, "Start Time is in the past", Toast.LENGTH_SHORT).show();
                }

                try{ booking.setEndTime(new Time(finish.getHour(),finish.getMinute(),0));}
                catch (NullPointerException npe){
                    Toast.makeText(TimeSelectActivity.this, "End Time is before Start Time, not saved", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("booking", booking);
                //Toast.makeText(getApplicationContext(),"Start: "+ start.getHour()+":"+start.getMinute()+"End: "+ finish.getHour()+":"+finish.getMinute(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Start: "+ booking.getStartTime()+"  End: "+ booking.getEndTime(), Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }
}