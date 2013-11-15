package com.example.CARDZ;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import cardsui.objects.CardStack;
import cardsui.views.CardUI;
import com.parse.*;

import java.util.List;


public class MyActivity extends Activity {

    
    private static final String GOLDEN = "#f2a400";
    private static final String RED = "#e00707";
    private static final String GREEN = "#4ac925";
    private static final String SKYBLUE = "#00d5f2";
    private static final String HOLOBLUE = "#ff33b6ea";

    String[] colorArray = {GOLDEN, RED, GREEN, SKYBLUE, HOLOBLUE};
    ParseObject topicObject;
    String topic;

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
        //topicObject.
        ParseUser user = ParseUser.getCurrentUser();
        ParseRelation<ParseObject> relation = user.getRelation("likes");
        List<ParseObject> cardObjects = null;
        try{
            cardObjects = relation.getQuery().find();
        }catch(ParseException e){
            e.printStackTrace();
        }


        final CardStack stack = new CardStack();
        stack.setTitle("Flashcards");
        mCardView.addStack(stack);
        for(int i = 0; i < cardObjects.size(); i ++){
            ParseObject cardobject = null;
            cardobject = (ParseObject)cardObjects.get(i);
            final MyPlayCard c = new MyPlayCard("Flash Card", "Description", colorArray[i%colorArray.length], colorArray[i%colorArray.length], false, true);
            String answer = cardobject.getString("answer");
            c.setAnswer("answer");
            c.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("DEBUG", "CARD CLICdKEd");
                    c.switchDescription();
                    stack.remove(stack.getCards().size()-1);
                    stack.add(c);
                    stack.getmAdapter().notifyDataSetChanged();
                    return false;
                }
            });
            stack.add(c);
            numCards++;


        }
//        for (int i =0; i < 20; i ++){
//            final MyPlayCard c = new MyPlayCard("Flash Card", "Description", colorArray[i%colorArray.length], colorArray[i%colorArray.length], false, true);
//            c.setAnswer("ANSWER");
//            c.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    Log.d("DEBUG", "CARD CLICdKEd");
//                    c.switchDescription();
//                    stack.remove(stack.getCards().size()-1);
//                    stack.add(c);
//                    stack.getmAdapter().notifyDataSetChanged();
//                    return false;
//                }
//            });
//            stack.add(c);
//            numCards++;
//        }


        mCardView.refresh();
        mCardView.scrollToCard(numCards);
        mCardView.scrollToBottom();

    }
}
