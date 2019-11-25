package com.example.danmu;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

/**
 * Created by anchaoguang on 2019-10-11.
 */
public class SurfaceViewImpl extends SurfaceView {
    public static final String TAG = "SurfaceViewImpl";

    private Paint mPaint;
    private Path mPath;

    public SurfaceViewImpl(Context context) {
        super(context);
        init();
    }

    public SurfaceViewImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewImpl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        // 一般布局控件会显式调用setWillNotDraw(true) ,即不会绘制。surfaceview默认不使用view中的ondraw绘制 surfaceView的控件界面。

        setWillNotDraw(false);      // 用draw绘制
        mPath = new Path();
        mPaint = new Paint();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);  // 抗锯齿
    }

        @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            mPath.moveTo(x,y);
            Log.i(TAG, "actin_down");
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE){
            mPath.lineTo(x,y);
            Log.i(TAG, "action_move");
        }
        postInvalidate();
        Log.i(TAG, "invalidate");

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);

        Log.i(TAG, "ondraw");
    }
}
