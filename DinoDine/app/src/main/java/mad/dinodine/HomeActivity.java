package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button bookNow, viewBooked;

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
               Intent create_Booking = new Intent(HomeActivity.this, PeopleSelectActivity.class);
                Booking bookingNow = new Booking();
                create_Booking.putExtra("bookingNow", bookingNow);
                startActivity(create_Booking);
            }
        });

        viewBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to table/list view screen
                Intent lookup_Bookings = new Intent(HomeActivity.this, TableViewListViewActivity.class);
                startActivity(lookup_Bookings);

            }
        });


    }
}
