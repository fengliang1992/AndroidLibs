package com.fltry.androidlibs.view.timeselect;

import android.os.Bundle;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

public class TimeSelectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("调用系统日期时间");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_time_select;
    }
}
