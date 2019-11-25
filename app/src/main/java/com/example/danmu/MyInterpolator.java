package com.example.danmu;

import android.animation.TimeInterpolator;

/**
 * Created by anchaoguang on 2019-10-15.
 */
public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return input;
    }
}
