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

    int framesPerSecond = 60;
    long animationDuration = 10000; // 10 seconds

    Matrix matrix = new Matrix(); // transformation matrix

    Paint paint = new Paint();    // your paint
    Paint coursePaint = new Paint(); //Paint for course colloring

    long startTime;
    Bitmap bmp;

    public MyView(Context context) {
        super(context);

        // start the animation:
        this.startTime = System.currentTimeMillis();
        paint.setColor(Color.DKGRAY);
        coursePaint.setColor(Color.RED);
        this.postInvalidate();

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

    protected Path onMyDraw(float time, Canvas canvas, Path course) {

        //matrix.postRotate(30 * elapsedTime/1000);        // rotate 30° every second
        matrix.postTranslate(-time, 0); // move time pixels to the left
        // other transformations...


        canvas.concat(matrix);        // call this before drawing on the canvas!!
        //course.transform(matrix);


        canvas.drawBitmap(bmp, 0, 0, paint);
        course.offset(time, 0);
        canvas.drawPath(course, coursePaint);


        //if(elapsedTime < animationDuration) this.postInvalidateDelayed( 1000 / framesPerSecond);
        getHolder().unlockCanvasAndPost(canvas);
        return course;
    }

}