package com.fltry.module.excption;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.fltry.module.excption.databinding.ActivityExcptionBinding;
import com.fltry.module.lib_common.BaseActivity;

public class ExcptionActivity extends BaseActivity {

    ActivityExcptionBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_excption;
    }

    @Override
    protected String title() {
        return "异常捕获";
    }

    @Override
    protected void initView() {
        mBinding = (ActivityExcptionBinding) dataBinding;
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
                return;
            }
        }
    }

    public void exception(View v) {
        Activity activity = null;
        activity.finish();
    }

    public void unresponse(View v) {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
