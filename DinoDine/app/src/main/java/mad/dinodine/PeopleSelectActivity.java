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

    private int numOfPpl = 1; //1 is selected by default when we load the page.. so set to 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_select);
        spinner = findViewById(R.id.spinner);
        confirmPeople = findViewById(R.id.confirmPeople);


        confirmPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                String msg = "";
                numOfPpl = Integer.parseInt(spinner.getSelectedItem().toString());
                if(numOfPpl <= 0){
                    valid = false;
                    msg +="Please select how many people this booking is for\n";
                }
                if(valid) {
                    //Create booking and store numOfPpl in it
                    Booking booking = new Booking();
                    booking.setNumOfPeople(numOfPpl);

                    //store booking object along with intent and pass onto next activity
                    Intent intent = new Intent(getApplicationContext(), SelectDateActivity.class);
                    intent.putExtra("booking", booking);
                    startActivity(intent);
                }else{Toast.makeText(getApplicationContext(),msg,(int) 0).show();}
            }
        });
    }
}