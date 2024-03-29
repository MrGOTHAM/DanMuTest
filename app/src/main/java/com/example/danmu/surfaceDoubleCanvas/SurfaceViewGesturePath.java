package com.example.danmu.surfaceDoubleCanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anchaoguang on 2019-10-24.
 */
public class SurfaceViewGesturePath extends SurfaceView {
    private Paint mPaint;
    private Path mPath;


    public SurfaceViewGesturePath(Context context) {
        super(context);
        init();
    }

    public SurfaceViewGesturePath(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewGesturePath(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            mPath.moveTo(x, y);
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE){
            mPath.lineTo(x, y);
        }

        drawCanvas();
        return super.onTouchEvent(event);
    }

    public void drawCanvas(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SurfaceHolder surfaceHolder = getHolder();
                Canvas canvas = surfaceHolder.lockCanvas();

                canvas.drawPath(mPath, mPaint);

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }).start();
    }
}
