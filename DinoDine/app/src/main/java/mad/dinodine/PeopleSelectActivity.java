package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PeopleSelectActivity extends AppCompatActivity {
    Button btns[], done;
    int numOfPeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_select);

        Intent intent = getIntent();
        final Booking bookingNow = (Booking) intent.getSerializableExtra("bookingNow");



        for(int x = 1; x<=3; x++) {
            String btnID = "button" + x;
            int idNum = getResources().getIdentifier(btnID, "id", getPackageName());
            btns[x] = findViewById(idNum);
            //TODO look up values for each btn; on click listener for each btn
            final int finalX = x;
            btns[x].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO change background to signal selected and unselect others
                        numOfPeople = finalX;

                    }
                });
        }



        Spinner spinner = (Spinner) findViewById(R.id.spinner4plus);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.PeopleNum, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


       //TODO code to get Spinner item if btns not selected

        done = findViewById(R.id.button6);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numOfPeople <= 0){
                    //Todo toast reminder to select an option
                }
                bookingNow.setNumOfPeople(numOfPeople);
            }
        });


    }
}
