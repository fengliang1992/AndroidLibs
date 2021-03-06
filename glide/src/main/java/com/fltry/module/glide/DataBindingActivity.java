package com.fltry.module.glide;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public abstract class DataBindingActivity<B extends ViewDataBinding> extends AppCompatActivity{
    public Context mContext;
    TextView mToolbarTitle;
    private Toolbar mToolbar;
    public B dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());

        mToolbar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }

        if (mToolbarTitle != null) {
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbarTitle.setText(null == title() ? "" : title());
        }
        initView();

    }


    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != mToolbar && isShowBacking()) {
            showBack();
        }
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        mToolbar.setNavigationIcon(com.fltry.module.lib_common.R.mipmap.fhj_hs);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    protected abstract int getLayoutId();

    protected abstract String title();

    protected abstract void initView();


}
