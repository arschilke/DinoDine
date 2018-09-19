package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button bookNow, viewBooked;
    private static final String TAG = HomeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        bookNow = findViewById(R.id.bookTable);
        viewBooked = findViewById(R.id.viewBookings);

        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Starting the book table intent --> go to people screen
                //TODO confirm name of people screen
               // Intent create_Booking = new Intent(this, PeopleActivity.class);
                //startActivity(create_Booking);
            }
        });

        viewBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to table/list view screen
                //TODO confirm name of screen 7
               // Intent lookup_Bookings = new Intent(this, LookupViewActivity.class);
               // startActivity(lookup_Bookings);
            }
        });

        Booking b = new Booking(new Guest(), 2, 19, 9, 2018, 6, 0);

        Log.d(TAG,b.toString());
    }
}
