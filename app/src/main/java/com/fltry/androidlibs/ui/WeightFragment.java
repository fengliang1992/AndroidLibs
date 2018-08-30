package com.fltry.androidlibs.ui;

import android.widget.ListView;

import com.fltry.androidlibs.R;

import java.util.ArrayList;

import butterknife.BindView;

public class WeightFragment extends ClassBeanFragemt {
    @BindView(R.id.weight_lv)
    ListView weightLv;

    @Override
    protected int getLayoutId() {
        return R.layout.view_widget;
    }

    @Override
    public ArrayList<ClassBean> getClasses() {
        return ((MainActivity) getContext()).allClasses.get(ClassUtils.WEIGHT_FRAGMENT);
    }

    @Override
    public ListView getLv() {
        return weightLv;
    }
}
