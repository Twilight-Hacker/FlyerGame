package com.galadar.flyergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameScreen extends SurfaceView {

    private Bitmap bmp;
    private SurfaceHolder holder;

    public GameScreen(Context context) {
        super(context);

        this.setDrawingCacheEnabled(true);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.testbg);

        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas c = holder.lockCanvas(null);
                onMyDraw(c);
                holder.unlockCanvasAndPost(c);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
    }

    public void bganimation(int time){
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bgbasis);
        Canvas canvas = getHolder().lockCanvas();
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        BitmapShader mShader1 = new BitmapShader(bmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(mShader1);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bgfull);
        if(canvas==null) Log.e("SysError", "Canvas Null");
        canvas.drawBitmap(bitmap, -time, 0, paint);
        getHolder().unlockCanvasAndPost(canvas);
    }

    protected void onMyDraw(Canvas canvas) {
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bgbasis);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        BitmapShader mShader1 = new BitmapShader(bmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(mShader1);
        canvas.drawPaint(paint); // .drawBitmap(bmp, 10, 10, paint);
    }

    public Bitmap get(){
        return this.getDrawingCache();
    }



}

