package com.example.CARDZ;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cardsui.objects.Card;

public class MyPlayCard extends Card {
    String title;
    String question;
    String answer;
    Boolean answerMode;
    int mode;

	public MyPlayCard(String titlePlay, String description, String color,
			String titleColor, Boolean hasOverflow, Boolean isClickable) {
		super(titlePlay, description, color, titleColor, hasOverflow,
				isClickable);
        this.title = titlePlay;
        this.question = description;
        this.answer = "";
        answerMode = false;
        //initialize();
    }

	@Override
	public View getCardContent(Context context) {
		View v = LayoutInflater.from(context).inflate(R.layout.card_play, null);
		
		((TextView) v.findViewById(R.id.title)).setText(titlePlay);
		((TextView) v.findViewById(R.id.title)).setTextColor(Color
				.parseColor(titleColor));
		((TextView) v.findViewById(R.id.description)).setText(description);
		((ImageView) v.findViewById(R.id.stripe)).setBackgroundColor(Color
				.parseColor(color));

		if (isClickable == true)
			((LinearLayout) v.findViewById(R.id.contentLayout))
					.setBackgroundResource(R.drawable.selectable_background_cardbank);

		if (hasOverflow == true)
			((ImageView) v.findViewById(R.id.overflow))
					.setVisibility(View.VISIBLE);
		else
			((ImageView) v.findViewById(R.id.overflow))
					.setVisibility(View.GONE);

		return v;
	}

    public String switchDescription(){
        if (mode%4>2){
            this.description = question;
            answerMode = false;
            mode+=1;
            mode = mode%4;
            Log.d("DEBUG", "SWITCHING DESCRIPTION TO Question");
        } else {
            this.description = answer;
            answerMode = true;
            mode+=1;
            mode = mode%4;
            Log.d("DEBUG", "SWITCHING DESCRIPTION TO ANSWER");
        }
        return this.description;
    }


    public void setAnswer(String a){
        this.answer = a;
    }

}
