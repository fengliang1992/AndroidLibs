package com.fltry.androidlibs.ui;

import android.widget.ListView;

import com.fltry.androidlibs.R;

import java.util.ArrayList;

import butterknife.BindView;

public class UtilFragment extends ClassBeanFragemt {
    @BindView(R.id.util_lv)
    ListView utilLv;
    @Override
    protected int getLayoutId() {
        return R.layout.view_util;
    }

    @Override
    public ArrayList<ClassBean> getClasses() {
        return ((MainActivity) getContext()).allClasses.get(ClassUtils.UTIL_FRAGMENT);
    }

    @Override
    public ListView getLv() {
        return utilLv;
    }

}
