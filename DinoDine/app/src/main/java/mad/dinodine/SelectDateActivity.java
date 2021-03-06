package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import java.sql.Date;


public class SelectDateActivity extends AppCompatActivity {

    //Date dateBooked = new Date(); //sets to current date and time.
    long dateBooked = new Date().getTime();

    private EditText inputDate;
    ImageButton confirm;
    CalendarView calView;
    Booking booking = null;
    long randomInt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_select);

        confirm = findViewById(R.id.confirmDate);
        calView = findViewById(R.id.calendarView);

        //retrieve booking object.
        Intent intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");
        //set date to today's date, incase user just hits confirm.
        booking.setDate(calView.getDate());



        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                //create a temporary calendar and set the time to the selected day.
                Calendar book = Calendar.getInstance();
                book.set(year,month,dayOfMonth); //book the date, with time at 1am, (instead of 12 as less confusing?? maybe)
                //getCalendar for date right now - (Think could use calView.getTime()?)
                Log.d("dateCalView: ", ""+calView.getDate());
                Calendar rn = Calendar.getInstance();
                rn.set(Calendar.HOUR_OF_DAY, 0);rn.set(Calendar.MINUTE, 0);rn.set(Calendar.SECOND, 0);
                Log.d("dateRN:",""+rn.getTimeInMillis());
                //set booked date to time of new calendar
                if(book.getTimeInMillis() < rn.getTimeInMillis()) // if chosen date is less  than current date (before currentDate)
                {Log.d("ERROR","BOOKED DATE IN PAST");}
                    //Toast.makeText(getApplicationContext(),"NO GOOD",(int) 0).show();}
                //else
                    //Toast.makeText(getApplicationContext(),"DATE IS GREAT",(int) 0).show();
                dateBooked = book.getTimeInMillis();

                //set booking date.
                try{
                    booking.setDate(dateBooked);
                }catch (NullPointerException npe){
                    Toast.makeText(SelectDateActivity.this, "This date is in the past", Toast.LENGTH_SHORT).show();
                }

                Toast toast = Toast.makeText(getApplicationContext(), "booking.getDateString(): " + booking.getDateString() + "\nbooking.getTimeString(): " + booking.getTime(), (int) 1);
                //toast.show();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeSelectActivity.class);
                intent.putExtra("booking", booking);
                startActivity(intent);
            }
        });
    }
}


