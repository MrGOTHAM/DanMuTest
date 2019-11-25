package com.example.danmu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anchaoguang on 2019-10-26.
 */
public class MainActivity_01 extends ViewGroup {

    public MainActivity_01(Context context) {
        super(context);
    }

    public MainActivity_01(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainActivity_01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = 0;     // 整个flowLayout所占高度
        int width = 0;      // 整个flowLayout所占宽度
        int lineWidth = 0;  // 每行总宽度
        int lineHeight = 0; // 每行最大高度

        int count = getChildCount();
        for (int i = 0; i<count; i++){
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);


            MarginLayoutParams lp = null;
            if (child.getLayoutParams() instanceof MarginLayoutParams){
                lp = (MarginLayoutParams) child.getLayoutParams(); // 得到 margin参数
            }

            // 必须在measurChild之后调才会有结果
            int childMeasureWidth = lp.leftMargin + lp.rightMargin + child.getMeasuredWidth();
            int childMeasureHeight = lp.topMargin + lp.bottomMargin + child.getMeasuredHeight();

            if (lineWidth + childMeasureWidth > measureWidth){

            width = Math.max(width, lineWidth);
            height += lineHeight;

            lineHeight = childMeasureHeight;
            lineWidth = childMeasureWidth;
            } else {
                // 累加值， 取 lineWidth的最大宽度， 同时取最大高度
                lineWidth += childMeasureWidth;
                lineHeight = Math.max(childMeasureHeight, lineHeight);
            }

            if (i == count-1 ){ // 最后一行
                height += lineHeight;
                width += lineWidth;
            }
        }
        // 属性为MeasureSpec.EXACTLY 时，高度确定
        // 属性为wrap_content 时，不确定，因此需计算控件自身大小
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth:width,
                            (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight:height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        int top = 0;
        int left = 0;

        for (int i = 0; i < count; i++){
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childMeasureWidth = lp.leftMargin + lp.rightMargin + child.getMeasuredWidth();
            int childMeasureHeight = lp.topMargin + lp.bottomMargin + child.getMeasuredHeight();

            if (childMeasureWidth + lineWidth > getMeasuredWidth()){
            top += lineHeight;
            left = 0;
            lineHeight = childMeasureHeight;
            lineWidth = childMeasureWidth;
            } else {
                lineHeight = Math.max(lineHeight, childMeasureHeight);
                lineWidth += childMeasureWidth;
            }

            int lc = left + lp.leftMargin;
            int tc = top + lp.topMargin;
            int bc = tc + child.getMeasuredHeight();
            int rc = lc + child.getMeasuredWidth();

            child.layout(lc, tc, rc, bc);

            // left置为下一个子控件的起点
            left += childMeasureWidth;
        }
    }
}
