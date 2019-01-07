package com.fltry.module.lib_common;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;

/**
 * 侧滑的父Activity
 */
public abstract class SwipeBackActivity extends AppCompatActivity {
    public SwipeBackLayout swipeBackLayout;

    @SuppressLint("InflateParams")
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        overridePendingTransition(R.anim.activity_ani_enter, 0);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().getDecorView().setBackgroundDrawable(null);
        swipeBackLayout = (SwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.base, null);
        swipeBackLayout.attachToActivity(this);
    }

    public void setSwipeBackEnable(boolean enable) {
        swipeBackLayout.setEnableGesture(enable);
    }

    /**
     * 返回键调成此方法
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.activity_ani_exist);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_ani_exist);
    }
}
