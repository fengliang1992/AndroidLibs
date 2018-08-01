package com.fltry.androidlibs.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.fltry.androidlibs.R;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends ButterknifeActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    public ArrayList<ArrayList<ClassBean>> allClasses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("第三方");
        allClasses = ClassUtils.getAllClasses();

        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), this, 3));
        radioGroup.check(R.id.btn1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        setToolBarTitle("第三方");
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.btn2:
                        setToolBarTitle("工具");
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.btn3:
                        setToolBarTitle("控件");
                        viewPager.setCurrentItem(2, false);
                        break;

                    default:
                        break;
                }
            }
        });

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
        return R.layout.activity_main;
    }

}
