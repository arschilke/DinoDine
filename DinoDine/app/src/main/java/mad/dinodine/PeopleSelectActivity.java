package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class PeopleSelectActivity extends AppCompatActivity {


    ImageButton confirmPeople;

    Spinner spinner;

    private int numOfPpl = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_select);
        spinner = findViewById(R.id.spinner);
        confirmPeople = findViewById(R.id.confirmPeople);


        confirmPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfPpl = Integer.parseInt(spinner.getSelectedItem().toString());
                if(numOfPpl <= 0){
                    Toast.makeText(getApplicationContext(), "Please select a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Create booking and store numOfPpl in it
                    Booking bookRecord = new Booking();
                    if(bookRecord.setNumOfPeople(numOfPpl)){
                        Toast.makeText(PeopleSelectActivity.this, "Number of People less than 1 - not saved", Toast.LENGTH_SHORT).show();
                    }


                    //store booking object along with intent and pass onto next activity
                    Intent intent = new Intent(getApplicationContext(), SelectDateActivity.class);
                    intent.putExtra("booking", bookRecord);
                    startActivity(intent);
                }
            }
        });
    }
}