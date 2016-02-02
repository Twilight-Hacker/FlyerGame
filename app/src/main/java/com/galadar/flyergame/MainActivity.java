package com.galadar.flyergame;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity{

    final static int sdkVer = Build.VERSION.SDK_INT;
    Handler BgHandler = new Handler(Looper.getMainLooper());
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RelativeLayout main = (RelativeLayout)findViewById(R.id.ScreenView);

        final GameScreen background = new GameScreen(this);
        RelativeLayout.LayoutParams bgparams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        main.addView(background, bgparams);


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
                character.setX(event.getX() - (character.getWidth() / 2));
                character.setY(event.getY() - (character.getHeight() / 2));
                character.invalidate();
                return true;
            }
        });

        r = new Runnable() {
            @Override
            public void run() {
                Canvas canvas = background.getHolder().lockCanvas();
                if(canvas!=null) background.bganimation(50, canvas);
                else Log.e("SysError", "CANVAS NULL");
                BgHandler.postDelayed(this, 100);
            }
        };

        r.run();

    }

    @Override
    public void onBackPressed(){
        BgHandler.removeCallbacks(r);
        super.onBackPressed();
    }
}
