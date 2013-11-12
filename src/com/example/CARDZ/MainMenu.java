package com.example.CARDZ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.parse.ParseAnalytics;

/**
 * Created with IntelliJ IDEA.
 * User: tony
 * Date: 6/15/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenu extends Activity {

    Button studyButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        studyButton = (Button) findViewById(R.id.studyButton);
        final Intent intent = new Intent(this, TopicList.class);

        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(intent);
            }
        });
        ParseAnalytics.trackAppOpened(getIntent());
        getActionBar().setDisplayShowHomeEnabled(false);
    }
}
