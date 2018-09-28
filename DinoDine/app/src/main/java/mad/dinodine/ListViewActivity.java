package mad.dinodine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ListViewActivity extends AppCompatActivity {

    EditText searchBox = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        searchBox = findViewById(R.id.search);

        //Below three lines just prevent focus going to search box in order to stop keyboard popping up.
        findViewById(R.id.textView5).setFocusable(true);
        findViewById(R.id.textView5).setFocusableInTouchMode(true);
        findViewById(R.id.textView5).requestFocus();


        searchBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            }
        });
    }
}