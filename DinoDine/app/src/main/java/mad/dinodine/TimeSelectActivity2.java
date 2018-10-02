package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeSelectActivity2 extends AppCompatActivity {

    TextView instr = null;
    ImageButton submit = null;
    TimePicker start, finish;

    Booking booking = null;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select2);

        Intent intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");

        instr = findViewById(R.id.instructions);
        submit = findViewById(R.id.submitTimeBtn);
        start = findViewById(R.id.StartWidget);
        finish = findViewById(R.id.FinishWidget);

        instr.setText("Select times for " + booking.getDateString());
        //start.setText(booking.getTime());
        //finish.setText(booking.getTime());

        String hrs = booking.getTime().substring(0,2);
        String mins = booking.getTime().substring(3);
        int h = Integer.parseInt(hrs);
        int m = Integer.parseInt(mins);

        if((m-30) < 0)
        {
            mins = "30";
        }
        else
        {
            mins = "00"; h += 1;
        }
        if(h < 10){hrs = "0" + h;}else{hrs = ""+h;}

        //start.setText(hrs+":"+mins);
        //start.

        if(++h < 10){hrs = "0" + h;}else{hrs = ""+h;}

       // finish.setText(hrs+":"+mins);

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //start time picker
            }
        });
        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //start time picker
            }
        });
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DetailFormActivity.class);
                intent.putExtra("booking", booking);
                //if(times are set)
                startActivity(intent);
                //else
                //toastmsg saying to select times.
            }
        });
    }
}