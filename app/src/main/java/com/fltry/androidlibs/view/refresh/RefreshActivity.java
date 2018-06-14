package com.fltry.androidlibs.view.refresh;

import android.os.Bundle;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

public class RefreshActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("刷新");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh;
    }
}
