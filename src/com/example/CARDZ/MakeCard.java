package com.example.CARDZ;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: tony
 * Date: 6/16/13
 * Time: 2:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class MakeCard extends Activity {
    Button confirmButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_make);
        getActionBar().setDisplayShowHomeEnabled(false);


        confirmButton = (Button)findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


    }
}
