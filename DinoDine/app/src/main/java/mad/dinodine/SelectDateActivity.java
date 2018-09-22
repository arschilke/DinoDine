package mad.dinodine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SelectDateActivity extends AppCompatActivity {

    private EditText inputDate;
    private Button confirm;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_select);

        inputDate = findViewById(R.id.pickDate);
        confirm = findViewById(R.id.confirmDate);

        //Just outputs the value in a toast msg when you click the box. (Change to onFocusChange?)
        inputDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(getApplicationContext(), inputDate.getText(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //Just takes you to the next screen, doesn't pass any information.
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent selectTime = new Intent(getApplicationContext(), TimeSelectActivity.class);
                startActivity(selectTime);
            }
        });


        //Place holder for class file for calendar page.
    }
}
