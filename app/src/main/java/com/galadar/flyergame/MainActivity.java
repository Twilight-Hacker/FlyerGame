package com.galadar.flyergame;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
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
    Path circles = new Path();
    float pX, pY, X, Y;
    boolean penDown;
    float speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RelativeLayout main = (RelativeLayout)findViewById(R.id.ScreenView);

        final MyView background = new MyView(this);
        //background.setBackground(getResources().getDrawable(R.drawable.testbg, getTheme()));
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
        pX = character.getX();
        pY = character.getY();
        circles.moveTo(pX, pY);
        penDown = false;
        speed = 2;

        r = new Runnable() {
            @Override
            public void run() {
                Canvas canvas = background.getHolder().lockCanvas();
                if(canvas!=null) {
                    if(!penDown){
                        X=X+speed;
                        circles.addCircle(X, Y, 30, Path.Direction.CW);
                    }
                    circles = background.onMyDraw(speed, canvas, circles);
                }
                else Log.e("SysError", "CANVAS NULL");
                BgHandler.postDelayed(this, 50);
            }
        };


        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case (MotionEvent.ACTION_DOWN):
                        X = event.getX();
                        Y = event.getY();
                        penDown=true;
                        break;
                    case (MotionEvent.ACTION_MOVE):
                        X = event.getX();
                        Y = event.getY();
                        //if (X <= pX) return true; pX = X;
                        circles.addCircle(X, Y, 30, Path.Direction.CW);
                    break;
                    case (MotionEvent.ACTION_UP):
                        X = event.getX();
                        Y = event.getY();
                        penDown=false;
                        break;
                }
                return true;
            }
        });

        r.run();

    }

    @Override
    public void onBackPressed(){
        BgHandler.removeCallbacks(r);
        super.onBackPressed();
    }

}
