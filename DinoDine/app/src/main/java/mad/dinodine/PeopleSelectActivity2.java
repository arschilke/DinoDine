package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

public class PeopleSelectActivity2 extends AppCompatActivity {


    ImageButton confirmPeople;

    Spinner spinner;

    private int numOfPpl = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_select2);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //String plusSign = "\u002B"; // unicode, when I had a plus as the top entry.

                spinner.setBackground(getResources().getDrawable(R.drawable.roundedbutton_active));
                numOfPpl = Integer.parseInt(parentView.getItemAtPosition(position).toString());
            }

            //Need to implement this as setOnItemSelectedListener is abstract.
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        confirmPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create booking and store numOfPpl in it
                Booking bookRecord = new Booking();
                bookRecord.setNumOfPeople(numOfPpl);

                //store booking object along with intent and pass onto next activity
                Intent intent = new Intent(getApplicationContext(), SelectDateActivity.class);
                intent.putExtra("booking", bookRecord);
                startActivity(intent);
            }
        });
    }
}