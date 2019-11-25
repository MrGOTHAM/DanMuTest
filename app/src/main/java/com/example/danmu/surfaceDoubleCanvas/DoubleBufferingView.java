package com.example.danmu.surfaceDoubleCanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anchaoguang on 2019-10-25.
 */
public class DoubleBufferingView extends SurfaceView {
    private Paint mPaint;
    public List<Integer> mInts = new ArrayList<>();

    public DoubleBufferingView(Context context) {
        super(context);
        init();
    }

    public DoubleBufferingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DoubleBufferingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);




    }


    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                drawText(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    private void drawText(final SurfaceHolder holder){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<10 ; i++){
                    Canvas canvas = holder.lockCanvas();
                    mInts.add(i);
                    for (int x:mInts){              // 每次都重绘之前画布上的数据
                        canvas.drawText(x+"", x *30, 50, mPaint);
                    }
                    holder.unlockCanvasAndPost(canvas);
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
