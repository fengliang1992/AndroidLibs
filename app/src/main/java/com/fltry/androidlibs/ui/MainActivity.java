package com.fltry.androidlibs.ui;

import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.fltry.androidlibs.R;
import com.fltry.module.lib_common.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private Unbinder unbinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public ArrayList<ArrayList<ClassBean>> allClasses;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected String title() {
        return "第三方";
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        allClasses = ClassUtils.getAllClasses();

        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), this, 3));
        radioGroup.check(R.id.btn1);
        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {

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
    protected boolean isShowBacking() {
        return false;
    }
}
