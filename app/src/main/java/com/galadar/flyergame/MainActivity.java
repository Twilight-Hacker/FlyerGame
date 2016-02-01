package com.galadar.flyergame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
*/


        RelativeLayout main = (RelativeLayout)findViewById(R.id.ScreenView);

        final ImageView character = new ImageView(this);

        character.setMaxHeight(35);
        character.setMaxWidth(30);
        character.setBackgroundColor(Color.BLUE);
        character.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(40, 45);
        params.addRule(RelativeLayout.CENTER_VERTICAL);

        main.addView(character, params);

        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                character.setX(event.getX()- (character.getWidth()/2) );
                character.setY(event.getY()- (character.getHeight()/2) );
                character.invalidate();
                return true;
            }
        });


    }
}
