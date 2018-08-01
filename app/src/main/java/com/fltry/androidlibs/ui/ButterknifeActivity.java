package com.fltry.androidlibs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fltry.module.lib_common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class ButterknifeActivity extends BaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
