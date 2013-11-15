package com.example.CARDZ;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.LoginButton;
import com.parse.*;

import java.util.Arrays;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: tony
 * Date: 6/15/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenu extends FragmentActivity {

    Button studyButton;
    LoginButton loginButton;
    Context context;
    Activity activity;
    ProgressDialog progressDialog;
    private boolean isResumed = false;
//    private UiLifecycleHelper uiHelper;
//        private Session.StatusCallback callback =
//            new Session.StatusCallback() {
//                @Override
//                public void call(Session session,
//                                 SessionState state, Exception exception) {
//                    onSessionStateChange(session, state, exception);
//                }
//            };


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        context = this;
        activity =this;

//        uiHelper = new UiLifecycleHelper(this, callback);
//        uiHelper.onCreate(savedInstanceState);



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

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFacebookButtonPress();
            }
        });

        checkUser();
    }

    private void checkUser(){
        // Check if there is a currently logged in user
        // and they are linked to a Facebook account.
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null)) {
            // Go to the user info activity
            studyButton.setVisibility(View.VISIBLE);
            loginButton.setState(true);
        } else {
            studyButton.setVisibility(View.GONE);
            loginButton.setState(false);
        }
        loginButton.invalidate();
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
//    }

    private void handleFacebookButtonPress(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser == null){
            onLoginButtonClicked();
        } else {
            onLogoutButtonClicked();
        }
        checkUser();
    }

    private void onLogoutButtonClicked(){
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            // Go to the user info activity
            studyButton.setVisibility(View.VISIBLE);
        } else {
            studyButton.setVisibility(View.GONE);
        }

    }

    private void onLoginButtonClicked() {
        MainMenu.this.progressDialog = ProgressDialog.show(
                MainMenu.this, "", "Logging in...", true);
        List<String> permissions = Arrays.asList("basic_info", "user_about_me",
                "user_relationships", "user_birthday", "user_location");
        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                MainMenu.this.progressDialog.dismiss();
                if (user == null) {
                    Log.d("DEBUG",
                            "Uh oh. The user cancelled the Facebook login.");
                    studyButton.setVisibility(View.GONE);

                } else if (user.isNew()) {
                    Log.d("DEBUG",
                            "User signed up and logged in through Facebook!");
                    studyButton.setVisibility(View.VISIBLE);

                } else {
                    Log.d("Debug",
                            "User logged in through Facebook!");
                    studyButton.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
//        uiHelper.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onResume() {
        super.onResume();
//        uiHelper.onResume();
        isResumed = true;
        checkUser();
    }

    @Override
    public void onPause() {
        super.onPause();
//        uiHelper.onPause();
        isResumed = false;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
//        uiHelper.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        uiHelper.onSaveInstanceState(outState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        // Only make changes if the activity is visible
        if (isResumed) {
            FragmentManager manager = getSupportFragmentManager();
            // Get the number of entries in the back stack
            int backStackSize = manager.getBackStackEntryCount();
            // Clear the back stack
            for (int i = 0; i < backStackSize; i++) {
                manager.popBackStack();
            }
            if (state.isOpened()) {
                // If the session state is open:
                // Show the authenticated fragment
//                showFragment(SELECTION, false);
                studyButton.setVisibility(View.VISIBLE);
            } else if (state.isClosed()) {
                // If the session state is closed:
                // Show the login fragment
//                showFragment(SPLASH, false);
                studyButton.setVisibility(View.GONE);

            }
        }
    }
//
//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//        Session session = Session.getActiveSession();
//
//        if (session != null && session.isOpened()) {
//            // if the session is already open,
//            // try to show the selection fragment
//            studyButton.setVisibility(View.VISIBLE);
////            showFragment(SELECTION, false);
//        } else {
//            // otherwise present the splash screen
//            // and ask the person to login.
////            showFragment(SPLASH, false);
//            studyButton.setVisibility(View.GONE);
//
//        }
//    }
//

}
