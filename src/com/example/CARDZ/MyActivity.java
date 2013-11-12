package com.example.CARDZ;

import android.app.Activity;
import android.os.Bundle;
import cardsui.objects.CardStack;
import cardsui.views.CardUI;


public class MyActivity extends Activity {

    
    private static final String GOLDEN = "#f2a400";
    private static final String RED = "#e00707";
    private static final String GREEN = "#4ac925";
    private static final String SKYBLUE = "#00d5f2";
    private static final String HOLOBLUE = "#ff33b6ea";

    CardUI mCardView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getActionBar().setDisplayShowHomeEnabled(false);

        mCardView = (CardUI) findViewById(R.id.cardsview);
        mCardView.setSwipeable(true);

        CardStack stack = new CardStack();
        stack.setTitle("Flashcards");
        mCardView.addStack(stack);
        stack.add(new MyPlayCard("Flash Card", "Description", RED, RED, true, true));
        stack.add(new MyPlayCard("Flash Card", "Description", GOLDEN, GOLDEN, true, true));
        stack.add(new MyPlayCard("Flash Card", "Description", GREEN, GREEN, true, true));
        stack.add(new MyPlayCard("Flash Card", "Description", SKYBLUE, SKYBLUE, true, true));
        for (int i =0; i < 10; i ++){
        stack.add(new MyPlayCard("Flash Card", "Description", HOLOBLUE, HOLOBLUE, true, true));
        }


        mCardView.refresh();
    }
}
