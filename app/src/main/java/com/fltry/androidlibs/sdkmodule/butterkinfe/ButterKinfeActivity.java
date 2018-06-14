package com.fltry.androidlibs.sdkmodule.butterkinfe;

import android.os.Bundle;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

public class ButterKinfeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("butterknife的使用");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_butter_kinfe;
    }


}
