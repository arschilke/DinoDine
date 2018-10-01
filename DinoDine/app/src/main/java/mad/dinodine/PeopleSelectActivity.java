package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class PeopleSelectActivity extends AppCompatActivity {

    Button num1;
    Button num2;
    Button num3;
    Button confirmPeople;

    Spinner spinner4plus;

    private int numOfPpl = 4;

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
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.peopleNum, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner4plus.setAdapter(adapter);

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make button 1 'active'
                numOfPpl = 1;
                num1.setBackground(getResources().getDrawable(R.drawable.roundedbutton_active));
                num2.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                num3.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                spinner4plus.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfPpl = 2;
                //make button 2 'active'
                num1.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                num2.setBackground(getResources().getDrawable(R.drawable.roundedbutton_active));
                num3.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                spinner4plus.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfPpl = 3;
                //make button 3 active;
                num1.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                num2.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                num3.setBackground(getResources().getDrawable(R.drawable.roundedbutton_active));
                spinner4plus.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
            }
        });

        spinner4plus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //String plusSign = "\u002B"; // unicode, when I had a plus as the top entry.

                    spinner4plus.setBackground(getResources().getDrawable(R.drawable.roundedbutton_active));
                    num1.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                    num2.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
                    num3.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
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
