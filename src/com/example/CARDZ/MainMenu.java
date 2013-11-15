package com.example.CARDZ;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseFacebookUtils;


/**
 * Created with IntelliJ IDEA.
 * User: tony
 * Date: 6/15/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenu extends FragmentActivity {

    Button studyButton;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        context = this;

        studyButton = (Button) findViewById(R.id.studyButton);
        final Intent intent = new Intent(this, TopicList.class);

        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(intent);
            }
        });
        ParseAnalytics.trackAppOpened(getIntent());
        Parse.initialize(this, "P8vTRXzXsHTGKI9X0g2pZaUwNeE35CKbBtXXDJHG", "zNcWTpND2CF1xSX0XO1HaBXlr0uXw7tiLL1gAY82");
        ParseFacebookUtils.initialize("365694706908029");

//        ParseFacebookUtils.logIn(this, new LogInCallback() {
//            @Override
//            public void done(ParseUser user, ParseException err) {
//                if (user == null) {
//                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
//                } else if (user.isNew()) {
//                    Log.d("MyApp", "User signed up and logged in through Facebook!");
//                    Toast.makeText(context, "Thanks for loggin in", Toast.LENGTH_SHORT).show();;
//
//                } else {
//                    Log.d("MyApp", "User logged in through Facebook!");
//                    Toast.makeText(context, "Thanks for loggin in", Toast.LENGTH_SHORT).show();;
//
//                }
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
//    }
}
