package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PeopleSelectActivity extends AppCompatActivity {

    Button num1;
    Button num2;
    Button num3;
    Button confirmPeople;

    Spinner spinner4plus;

    private int numOfPpl = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_select);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        confirmPeople = findViewById(R.id.confirmPeople);
        spinner4plus = findViewById(R.id.spinner4plus);

        //Pulls values from strings.xml [string-array: peopleNum) for number of people.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.peopleNum, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4plus.setAdapter(adapter);

        confirmPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent create_Booking = new Intent(getApplicationContext(), SelectDateActivity.class);
                Booking bookingNow = new Booking();
                create_Booking.putExtra("bookingNow", bookingNow);
                startActivity(create_Booking);
            }
        });


    }
}