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

        if (Build.VERSION.SDK_INT >= 23) {//如果是6.0以上的
            //验证是否许可权限
            if (ActivityCompat.checkSelfPermission(this
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                ActivityCompat.requestPermissions(this
                        , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return;
            }
        }
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
