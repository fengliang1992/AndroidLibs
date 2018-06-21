package com.fltry.androidlibs.utils.excption;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import butterknife.OnClick;

public class ExcptionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("异常捕获");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_excption;
    }

    @OnClick({R.id.excption_btn1, R.id.excption_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.excption_btn1:
                Activity activity = null;
                activity.finish();
                break;
            case R.id.excption_btn2:
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}
