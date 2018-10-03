package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailFormActivity extends AppCompatActivity {
    Spinner article, DietRestrict;
    EditText fName, lName, phoneNum, emailET, phoneExt;
    Button submitBtn;
    Intent intent;
    Booking booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form);

        intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");

       // article = findViewById(R.id.article);
       // DietRestrict = findViewById(R.id.dietRestrict);

       // phoneExt = findViewById(R.id.phoneExt);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        phoneNum = findViewById(R.id.phone);
        emailET = findViewById(R.id.email);

        submitBtn = findViewById(R.id.submitBtn);


        //TODO need to sent data to match with previous pages
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating Guest, need to add to Booking as well

                Guest PersonInfo = new Guest(fName.getText().toString(),lName.getText().toString(), phoneNum.getText().toString(),emailET.getText().toString());

                //booking = (Booking) intent.getSerializableExtra("bookingNow");
                booking.setGuest(PersonInfo);

                //Allocate Table
                Toast.makeText(getApplicationContext(),"Booking successful!",(int) 1).show();
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });





    }
}
