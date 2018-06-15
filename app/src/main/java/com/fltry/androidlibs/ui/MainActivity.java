package com.fltry.androidlibs.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.fltry.androidlibs.R;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    public ArrayList<ArrayList<ClassBean>> allClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("第三方");

        allClasses = ListUtils.getAllClasses();

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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
