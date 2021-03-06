package mad.dinodine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TimeSelectActivity extends AppCompatActivity {
    Button start[], finish[];
    TextView DateTV;
    ImageButton submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);


        TextView DateTV = findViewById(R.id.DateText);
        //TODO retreive value for Date TextView
        DateTV.setText("Set Date");

        //array for start btns
        for(int x = 1; x <= 9; x++){
            String btnID = "btn" + x;
            int idNum = getResources().getIdentifier(btnID, "id", getPackageName());
            start[x] = findViewById(idNum);
            //TODO look up values for each btn; on click listener for each btn
            start[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO change background to signal selected and unselect others
                    //TODO save value to variable

                }
            });
        }
        //array for finish btns
        for(int x = 1; x <= 3; x++){
            String btnID = "FBtn" + x;
            int idNum = getResources().getIdentifier(btnID, "id", getPackageName());
            finish[x] = findViewById(idNum);
            //TODO look up values for each btn; on click listener for each btn
            finish[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO auto select start + default setting
                    //TODO change background to signal selected and unselect others
                    //TODO save value to variable (ask how storing in DB)

                }
            });
        }

        submitBtn = findViewById(R.id.submitTimeBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getParentActivityIntent();

            }
        });
    }
}
