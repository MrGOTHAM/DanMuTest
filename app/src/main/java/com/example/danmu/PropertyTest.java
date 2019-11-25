package com.example.danmu;

import android.animation.*;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

/**
 * Created by anchaoguang on 2019-10-15.
 */
public class PropertyTest extends AppCompatActivity {
    private final static String TAG = "an";
    private Context mContext;
    private TextView acg;
    private Button click;
    private TextView ancg;
    int index = 0;
    private AnimatorSet animatorSet;
    private LinearLayout linearLayout;

    private Random random = new Random();

    private final String[] PEOPLE = {"安超广", "王大麻子",
            "葫芦娃", "蔡徐坤","大娃","二娃","三娃","四娃"
    };

    private int getPeopleFollow() {
        if (index < PEOPLE.length){
            return index;
        }
        return -1;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview);
        mContext = getApplication();
        acg = findViewById(R.id.acg);
        ancg = findViewById(R.id.ancg);
        linearLayout = findViewById(R.id.linear);
        click = findViewById(R.id.click_button);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });


    }

    private void doClick() {
        doAnimators(acg, dip2px(mContext, 24), dip2px(mContext, 24) - dip2px(mContext, 55), 0,linearLayout);
        doAnimators(ancg, 0, -dip2px(mContext, 55), 2000,linearLayout);
    }

    public void doAnimators(final TextView view, float startY, float endY, long delay , final View linear) {

        PropertyValuesHolder translation1 = PropertyValuesHolder.ofFloat("translationY",  startY, endY);
        PropertyValuesHolder alpha1 = PropertyValuesHolder.ofFloat("alpha",0f , 1f, 0f);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                linear.setVisibility(View.VISIBLE);
                int i = getPeopleFollow();
                if (i < PEOPLE.length  && i> -1){
                    view.setText(PEOPLE[i] + "已查看点评!");
                    index++;
                }else {
                    index = 0;
                    view.setText(PEOPLE[index] + "已查看点评!");
                    index ++;
                }
            }
        });

        final ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, translation1, alpha1);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd");


            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i(TAG, "onAnimationCancle");
                if (index == PEOPLE.length +1){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            linear.setVisibility(View.GONE);
                        }
                    });
                } else if (index == PEOPLE.length){
                    index++;
                }

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i(TAG, "onAnimationRepeat");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int i = getPeopleFollow();
                        if (i == -1){
                                animator.cancel();
                        }else {
                            view.setText(PEOPLE[i] + "已查看点评!");
                            index++;
                        }
                    }
                });
            }
        });

        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setStartDelay(delay);
        animator.start();
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((double) dipValue * (double) scale + 0.5);
    }
}
