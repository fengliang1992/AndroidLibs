package com.fltry.androidlibs.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class ClassBeanFragemt extends Fragment {

    private Unbinder unbinder;
    public View mRootView;
    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = View.inflate(getContext(), getLayoutId(), null);
        } else {// 移除view之前的fragment
            ViewParent parent = mRootView.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(mRootView);
            }
        }
        initView();
        return mRootView;
    }

    protected abstract int getLayoutId();

    private void initView(){
        unbinder = ButterKnife.bind(this, mRootView);
        final ArrayList<ClassBean> classes = getClasses();
        getLv().setAdapter(new MyClassAdapter(getContext(), classes));

        getLv().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > classes.size() - 1) {
                    Toast.makeText(getContext(), "index越界", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(Intent.ACTION_VIEW, classes.get(position).getUri()));
            }
        });
    }

    public abstract ArrayList<ClassBean> getClasses();

    public abstract ListView getLv();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
