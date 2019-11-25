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
 * Created by anchaoguang on 2019-10-14.
 */
public class ValueAnimatoran extends AppCompatActivity {
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

        final ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "alpha", 0, 1, 0);
        final ObjectAnimator animatorTran1 = ObjectAnimator.ofFloat(view, "translationY", startY, endY);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int i = getPeopleFollow();
                if (i < PEOPLE.length  && i> -1){
                    view.setText(PEOPLE[i] + "已查看点评!");
                    index++;
                }else {
                    index = 0;
                    view.setText(PEOPLE[index] + "已查看点评!");

                }
            }
        });

        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animatorTran1.setRepeatMode(ValueAnimator.RESTART);
        animatorTran1.setRepeatCount(ValueAnimator.INFINITE);

        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(final Animator animation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (index<PEOPLE.length && linear.getVisibility() == View.INVISIBLE){
                        linear.setVisibility(View.VISIBLE);
                        ancg.setVisibility(View.VISIBLE);
                        acg.setVisibility(View.VISIBLE);
                    }
                    int i = getPeopleFollow();
                    if (i == -1){
//                        animator1.cancel();
//                        animatorTran1.cancel();
//                        animatorSet.cancel();

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    acg.post(new Runnable() {
                                        @Override
                                        public void run() {
                                        acg.setVisibility(View.GONE);
                                        }
                                    });
                                    Thread.sleep(2000);
                                    linear.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ancg.setVisibility(View.GONE);
                                            linear.setVisibility(View.INVISIBLE);

                                        }
                                    });
//                                    linear.setVisibility(View.GONE);
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }

                            }
                        });
                        thread.start();


                    }else {
                        view.setText(PEOPLE[i] + "已查看点评!");
                        index++;
                    }
                }
            });

            }
        });

        animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(animator1).with(animatorTran1);
        animatorSet.setDuration(4000);

        animatorSet.start();
        Log.i(TAG, view.getMeasuredHeight() + "高度=========");

        animatorSet.setStartDelay(delay);

    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((double) dipValue * (double) scale + 0.5);
    }
}
