package com.example.CARDZ;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import cardsui.objects.CardStack;
import cardsui.views.CardUI;


public class MyActivity extends Activity {

    
    private static final String GOLDEN = "#f2a400";
    private static final String RED = "#e00707";
    private static final String GREEN = "#4ac925";
    private static final String SKYBLUE = "#00d5f2";
    private static final String HOLOBLUE = "#ff33b6ea";

    String[] colorArray = {GOLDEN, RED, GREEN, SKYBLUE, HOLOBLUE};

    CardUI mCardView;
    int numCards;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mCardView = (CardUI) findViewById(R.id.cardsview);
        mCardView.setSwipeable(true);

        CardStack stack = new CardStack();
        stack.setTitle("Flashcards");
        mCardView.addStack(stack);
        for (int i =0; i < 20; i ++){
            final MyPlayCard c = new MyPlayCard("Flash Card", "Description", colorArray[i%colorArray.length], colorArray[i%colorArray.length], false, true);
            c.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("DEBUG", "CARD CLICKEd");
                    c.switchDescription();
                    return true;
                }
            });
            stack.add(c);
            numCards++;
        }


        mCardView.refresh();
        mCardView.scrollToCard(numCards);
        mCardView.scrollToBottom();

    }
}
