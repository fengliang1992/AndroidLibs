package com.fltry.androidlibs.ui;

import android.widget.ListView;

import com.fltry.androidlibs.R;

import java.util.ArrayList;

import butterknife.BindView;

public class SdkFragment extends ClassBeanFragemt {
    @BindView(R.id.sdk_lv)
    ListView sdkLv;

    @Override
    protected int getLayoutId() {
        return R.layout.view_sdk;
    }

    @Override
    public ArrayList<ClassBean> getClasses() {
        return ((MainActivity) getContext()).allClasses.get(ClassUtils.SDK_FRAGMENT);
    }

    @Override
    public ListView getLv() {
        return sdkLv;
    }
}
