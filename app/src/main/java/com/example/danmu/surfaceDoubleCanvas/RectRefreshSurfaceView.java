package com.example.danmu.surfaceDoubleCanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anchaoguang on 2019-10-25.
 */
public class RectRefreshSurfaceView extends SurfaceView {
    private final static String TAG = "RectRefreshSurfaceView";

    private Paint mPaint;
    public RectRefreshSurfaceView(Context context) {
        super(context);
        init();
    }

    public RectRefreshSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectRefreshSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.argb(0x1f,0xff, 0xff, 0xff));
        mPaint.setTextSize(30);

        getHolder().addCallback(new SurfaceHolder.Callback() {
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
        new Thread(new Runnable() {
            @Override
            public void run() {
//                // 先要清屏
//                while (true){
//                    Rect dirtyRect = new Rect(0, 0, 1, 1);
//                    Canvas canvas = holder.lockCanvas(dirtyRect);
//                    Rect canvasRect = canvas.getClipBounds();
//
//                    if (getWidth() == canvasRect.width() && getHeight() == canvasRect.height()){
//                        canvas.drawColor(Color.BLACK);
//                        holder.unlockCanvasAndPost(canvas);
//                    } else {
//                        holder.unlockCanvasAndPost(canvas);
//                        break;
//                    }
//                }

                // 画图
                for (int i = 0; i<10; i++){

                    // 画大方
                    if ( i == 0 ){
                        Canvas canvas = holder.lockCanvas(new Rect(10 ,10, 600, 600));
                        canvas.drawColor(Color.RED);
                        dumpCanvasRect(canvas);
                        holder.unlockCanvasAndPost(canvas);
                    }

                    // 画中方
                    if (i == 1){
                        Canvas canvas = holder.lockCanvas(new Rect(30, 30, 570, 570));
                        canvas.drawColor(Color.GREEN);
                        dumpCanvasRect(canvas);
                        holder.unlockCanvasAndPost(canvas);
                    }

                    // 画小方
                    if (i == 2){
                        Canvas canvas = holder.lockCanvas(new Rect(60, 60, 540, 540));
                        canvas.drawColor(Color.BLUE);
                        dumpCanvasRect(canvas);
                        holder.unlockCanvasAndPost(canvas);
                    }

                    // 画圆形
                    if (i == 3){
                        Canvas canvas = holder.lockCanvas(new Rect(200, 200, 400, 400));
                        mPaint.setColor(Color.BLACK);
                        dumpCanvasRect(canvas);
                        canvas.drawCircle(300, 300, 100, mPaint);
                        holder.unlockCanvasAndPost(canvas);
                    }

                    // 写数字
                    if (i == 4){
                        Canvas canvas = holder.lockCanvas(new Rect(250, 250, 350, 350));
                        mPaint.setColor(Color.RED);
                        dumpCanvasRect(canvas);
                        canvas.drawText("6", 300 , 300 ,mPaint);
                        holder.unlockCanvasAndPost(canvas);
                    }

                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void dumpCanvasRect(Canvas canvas){
        if (canvas != null){
            Rect rect = canvas.getClipBounds(); // 检索当前画布坐标系
            Log.i(TAG,"left: "+rect.left + "right :" + rect.right + "top: "+ rect.top + "bottom :"+ rect.bottom);
        }
    }
}
