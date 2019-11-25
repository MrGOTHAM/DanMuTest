package com.example.danmu;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



/**
 * Created by anchaoguang on 2019-10-15.
 */
public class ClassView extends AppCompatActivity {
    private final static String TAG = "an";
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_view_layout);
        mContext = getApplication();

    }

}
