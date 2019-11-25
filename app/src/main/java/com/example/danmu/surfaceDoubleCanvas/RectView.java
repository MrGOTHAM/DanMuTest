package com.example.danmu.surfaceDoubleCanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anchaoguang on 2019-10-25.
 */
public class RectView extends View {
    private Paint mPaint;

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画大方
        mPaint.setColor(Color.RED);
        canvas.drawRect(new Rect(10, 10, 600, 600), mPaint);

        // 画中方
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(new Rect(30, 30, 570, 570), mPaint);

        // 画小方
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(60, 60, 540, 540), mPaint);

        // 画圆形
        mPaint.setColor(Color.argb(0x3f, 0xff, 0xff, 0xff));
        canvas.drawCircle(300, 300, 100, mPaint);

        // 写数字
        mPaint.setColor(Color.GREEN);
        canvas.drawText("6", 300, 300, mPaint);

    }
}
