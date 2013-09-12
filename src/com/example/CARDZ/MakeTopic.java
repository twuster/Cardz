package com.example.CARDZ;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created with IntelliJ IDEA.
 * User: tony
 * Date: 6/16/13
 * Time: 2:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class MakeTopic extends Activity {
    Button confirmButton;
    EditText topicText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_make);
        Parse.initialize(this, "P8vTRXzXsHTGKI9X0g2pZaUwNeE35CKbBtXXDJHG", "zNcWTpND2CF1xSX0XO1HaBXlr0uXw7tiLL1gAY82");
        topicText = (EditText) findViewById(R.id.topic_name);
        confirmButton = (Button) findViewById(R.id.tconfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(topicText.getText().toString().equals(""))) {
                    ParseObject testObject = new ParseObject("Topics");
                    testObject.put("topic", topicText.getText().toString());
                    testObject.saveInBackground();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(v.getContext(), "Please Enter a Topic", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}
