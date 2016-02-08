package com.galadar.flyergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PathView extends SurfaceView{
    Paint paint;
    Paint coursePaint;

    public PathView(Context context) {
        super(context);

        // start the animation:
        paint.setAlpha(0);
        coursePaint.setColor(Color.RED);

        this.postInvalidate();

        this.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas c = holder.lockCanvas(null);
                c.drawPaint(paint);
                holder.unlockCanvasAndPost(c);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
    }

    public void onPathDraw(Canvas canvas, Path course){
        canvas.drawPath(course, coursePaint);
    }

}
