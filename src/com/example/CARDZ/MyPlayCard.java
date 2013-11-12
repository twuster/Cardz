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

	public MyPlayCard(String titlePlay, String description, String color,
			String titleColor, Boolean hasOverflow, Boolean isClickable) {
		super(titlePlay, description, color, titleColor, hasOverflow,
				isClickable);
        this.title = titlePlay;
        this.question = description;
        this.answer = "";
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

//    private void initialize(){
//        Log.d("DEBUG", "ONCLICKLISTENER SET");
//        this.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v == (LinearLayout) v.findViewById(R.id.contentLayout)){
//                    Log.d("DEBUG", "PRESSING ContENT LAYOUTTT");
//                } else if (v == (ImageView) v.findViewById(R.id.overflow)){
//                    Log.d("DEBUG", "PRESSIN OVERFLOW LAYOUTTT");
//                } else {
//                    Log.d("DEBUG", "CLICKED SOmethING");
//                }
//            }
//        });
//    }

    public void switchDescription(){
        Log.d("DEBUG", "SWITCHING DESCRIPTION");
    }


    public void setAnswer(String a){

    }

}
