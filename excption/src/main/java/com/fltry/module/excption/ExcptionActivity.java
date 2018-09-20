package com.fltry.module.excption;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.fltry.module.lib_common.BaseActivity;

public class ExcptionActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 模拟奔溃
     */
    private Button mExcptionBtn1;
    /**
     * 模拟ANRs
     */
    private Button mExcptionBtn2;

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
        mExcptionBtn1 = (Button) findViewById(R.id.excption_btn1);
        mExcptionBtn1.setOnClickListener(this);
        mExcptionBtn2 = (Button) findViewById(R.id.excption_btn2);
        mExcptionBtn2.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.excption_btn1) {
            Activity activity = null;
            activity.finish();
        } else if (v.getId() == R.id.excption_btn2) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
