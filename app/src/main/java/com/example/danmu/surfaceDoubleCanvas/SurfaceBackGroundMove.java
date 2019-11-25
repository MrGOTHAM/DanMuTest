package com.example.danmu.surfaceDoubleCanvas;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.danmu.R;

/**
 * Created by anchaoguang on 2019-10-24.
 */
public class SurfaceBackGroundMove extends SurfaceView {

    private SurfaceHolder mSurfaceHolder;
    private boolean flag = false;
    private Bitmap mBigmap;         //背景图

    private float mSurfaceViewWith;
    private float mSurfaceViewHeight;
    private int mBitposX;
    private Canvas mCanvas;

    private final int BITMAP_STEP = 1;    //背景画布移动步伐

    private enum State {
        LEFT, RIGHT
    }

    private State state = State.LEFT;

    public SurfaceBackGroundMove(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                flag = true;
                startMyAnimation();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                flag = false;
            }
        });
    }

    public void startMyAnimation() {
        mSurfaceViewWith = getWidth();
        mSurfaceViewHeight = getHeight();
        int width = (int) (mSurfaceViewWith * (3 / 2));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture1);
        mBigmap = Bitmap.createScaledBitmap(bitmap, width, (int) mSurfaceViewHeight, true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    mCanvas = mSurfaceHolder.lockCanvas();
                    DrawView();
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();


    }

    /**
     * 绘制背景
     */
    private void DrawView() {
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);    // 清空屏幕
        mCanvas.drawBitmap(mBigmap, mBitposX, 0, null); // 绘制当前屏幕背景

        // 图片滚动效果

        switch (state) {
            case LEFT:
                mBitposX -= BITMAP_STEP; // 画布左移
                break;
            case RIGHT:
                mBitposX += BITMAP_STEP; // 画布右移
                break;
            default:
                break;
        }
        if (mBitposX <= -mSurfaceViewWith / 2){
            state = State.RIGHT;
        }

        if (mBitposX >= 0){
            state = State.LEFT;
        }
    }
}
