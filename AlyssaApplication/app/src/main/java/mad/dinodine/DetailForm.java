package mad.dinodine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DetailForm extends AppCompatActivity {
    Spinner article, phoneExt;
    EditText fName, lName, phoneNum, emailET;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form);

        article = findViewById(R.id.article);
        phoneExt = findViewById(R.id.phoneExt);

        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        phoneNum = findViewById(R.id.phone);
        emailET = findViewById(R.id.email);



        submitBtn = findViewById(R.id.submitBtn);


        //TODO need to sent data to match with previous pages
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //example code, missing previous pages info
                Details PersonInfo = new Details(fName.getText().toString(),lName.getText().toString(), phoneNum.getText().toString(),
                        phoneExt.getSelectedItem().toString(), article.getSelectedItem().toString(), emailET.getText().toString());
                //TODO create DB handler to add the new Details to DB/associate with booking
            }
        });





    }
}
