package com.galadar.flyergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class MyView extends SurfaceView {

    Matrix matrix = new Matrix(); // transformation matrix
    Matrix inverse = new Matrix();

    Paint paint = new Paint();    // your paint
    Paint coursePaint = new Paint(); //Paint for course colloring
    Paint textPaint = new Paint();

    long startTime;
    float loc;
    Bitmap bmp;

    public MyView(Context context) {
        super(context);

        // start the animation:
        this.startTime = System.currentTimeMillis();
        paint.setColor(Color.DKGRAY);
        coursePaint.setColor(Color.RED);
        this.postInvalidate();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20f);
        loc=0f;

        this.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas c = holder.lockCanvas(null);
                bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bgfull);
                Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                BitmapShader mShader1 = new BitmapShader(bmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                paint.setShader(mShader1);
                c.drawBitmap(bmp, 10, 10, paint);
                holder.unlockCanvasAndPost(c);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
    }

    protected void onMyDraw(float time, Canvas canvas, Path course, String text) {

        //matrix.preTranslate(-time, 0); // move time pixels to the left
        //inverse.postTranslate(time, 0);
        loc+=time;
        // other transformations...

        //canvas.concat(matrix);        // call this before drawing on the canvas!!
        //course.transform(matrix);
        course.offset(-time, 0);

        canvas.drawBitmap(bmp, -loc, 0, paint);
        canvas.drawPath(course, coursePaint);

        //canvas.concat(inverse);

        canvas.drawText(text, 20, 20, textPaint);

        getHolder().unlockCanvasAndPost(canvas);
    }

}