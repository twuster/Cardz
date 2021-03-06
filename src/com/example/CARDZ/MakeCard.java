package com.example.CARDZ;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;


/**
 * Created with IntelliJ IDEA.
 * User: tony
 * Date: 6/16/13
 * Time: 2:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class MakeCard extends Activity {
    Button confirmButton;
    String topic;
    ParseObject topicObject;
    EditText editTitle;
    EditText editDesc;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_make);
        getActionBar().setDisplayShowHomeEnabled(false);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editDesc = (EditText) findViewById(R.id.editDesc);

        Bundle extras = getIntent().getExtras();
        if(extras!= null){
            topic = extras.getString("Topic");
        }
        Log.d("MAKECARD", "The topic is " + topic);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Topics");
        query.whereEqualTo("topic", topic);
        try{
            topicObject = query.getFirst();
        }
        catch(ParseException e){
            e.printStackTrace();;
        }

        confirmButton = (Button)findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                if (!(editTitle.getText().toString().equals("")) && !(editDesc.getText().toString().equals(""))) {
                    ParseObject flashCard = new ParseObject("Flashcards");
                    flashCard.put("question", editTitle.getText().toString());
                    flashCard.put("answer", editDesc.getText().toString());
                    ParseRelation<ParseObject> relation = topicObject.getRelation("cards");
                    flashCard.setObjectId(""+ Math.random()*10000);
                    relation.add(flashCard);
                    topicObject.saveInBackground();
                    finish();
                } else {
                    Toast.makeText(v.getContext(), "Please Complete All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
