package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
//import java.sql.Date;


public class SelectDateActivity extends AppCompatActivity {

    //Date dateBooked = new Date(); //sets to current date and time.
    long dateBooked = new Date().getTime();
    private EditText inputDate;
    private Button confirm;
    CalendarView calView;
    Booking booking = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_select);

        inputDate = findViewById(R.id.pickDate);
        confirm = findViewById(R.id.confirmDate);
        calView = findViewById(R.id.calendarView);

        //retrieve booking object.
        Intent intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");
        Toast toast = Toast.makeText(getApplicationContext(), "booking: " + booking.toString(), (int) 0);
        toast.show();
        toast = Toast.makeText(getApplicationContext(),"dateBooked: " + dateBooked, (int) 1);
        toast.show();

        calView.setDate(dateBooked);
        //booking.setDate(dateBooked);
        //booking.setDate(calView.getDate());




        //Just outputs the value in a toast msg when you click the box. (Change to onFocusChange?)
        //keyboard doesn't drop down when click done.
        inputDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(getApplicationContext(), inputDate.getText(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                dateBooked = calView.getDate();
                dateBooked = new Date(year,month,dayOfMonth).getTime();
                Toast toast = Toast.makeText(getApplicationContext(), "dateSelected: " + dayOfMonth + "/" + month + "/" + year, (int) 0);
                toast.show();
                booking.setDate(dateBooked);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                booking.setDate(dateBooked);
                Toast toast = Toast.makeText(getApplicationContext(),"date: " + new Date(dateBooked), (int) 0);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), TimeSelectActivity.class);
                intent.putExtra("booking", booking);
                startActivity(intent);
            }
        });


        //Place holder for class file for calendar page.
    }
}


