package mad.dinodine;

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

                //TODO Start the book table sequence --> go to people screen
            }
        });

        viewBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to table/list view screen
            }
        });


    }
}
