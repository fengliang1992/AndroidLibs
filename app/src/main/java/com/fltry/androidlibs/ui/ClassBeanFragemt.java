package com.fltry.androidlibs.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fltry.module.lib_common.AlertDialogUtils;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    private void initView() {
        unbinder = ButterKnife.bind(this, mRootView);
        final ArrayList<ClassBean> classes = getClasses();
        getLv().setAdapter(new MyClassAdapter(getContext(), classes));

        getLv().setOnItemClickListener((parent, view, position, id) -> {
            if (position > classes.size() - 1) {
                Toast.makeText(getContext(), "index越界", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, classes.get(position).getUri(mContext)));
            } catch (Exception e) {
                e.printStackTrace();
                String msg;
                if (e.getMessage().contains("No Activity found"))
                    msg = "未找到相应的界面，请确认当前是否为组件模式";
                else
                    msg = "由于65535报错，SDK暂时组件化运行";
                AlertDialogUtils.getMyAlert(mContext, "跳转失败", msg).show();
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
