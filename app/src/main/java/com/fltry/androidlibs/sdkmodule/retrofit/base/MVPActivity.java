package com.fltry.androidlibs.sdkmodule.retrofit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fltry.androidlibs.ui.BaseActivity;

public abstract class MVPActivity<V, T extends BasePresenter<V>> extends BaseActivity {
    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
