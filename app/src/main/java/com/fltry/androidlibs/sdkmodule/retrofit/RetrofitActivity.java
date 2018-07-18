package com.fltry.androidlibs.sdkmodule.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.sdkmodule.retrofit.base.MVPActivity;
import com.fltry.androidlibs.ui.BaseActivity;

public class RetrofitActivity extends MVPActivity<IResult,LoginPresenter> implements IResult{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("Retrofit+rxandroid+MVP");
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit;
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void loginSucceed() {

    }

    @Override
    public void loginFailed() {

    }
}
