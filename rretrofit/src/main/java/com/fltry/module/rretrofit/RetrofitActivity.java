package com.fltry.module.rretrofit;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;

import com.fltry.module.rretrofit.base.MVPActivity;
import com.fltry.module.rretrofit.databinding.ActivityRetrofitBinding;


public class RetrofitActivity extends MVPActivity<ILoginView, LoginPresenter> implements ILoginView {
    public static final String BASE_URL = "http://192.168.100.109:8004";
    ActivityRetrofitBinding mBinding;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected String title() {
        return "Retrofit+rxandroid+MVP";
    }


    @Override
    public void showDialog() {
        mBinding.retrofitTv.setText("正在请求");
        mBinding.retrofitTv.setTextColor(Color.GREEN);
        mBinding.retrofitBtn.setClickable(false);
    }

    @Override
    public void dismissDialog() {
        mBinding.retrofitTv.setText("请求结束");
        mBinding.retrofitTv.setTextColor(Color.GRAY);
        mBinding.retrofitBtn.setClickable(true);
    }

    @Override
    public void loginSucceed(String mse) {
        new AlertDialog.Builder(mContext).setMessage(mse).show();
    }

    @Override
    public void loginFailed(String error) {
        new AlertDialog.Builder(mContext).setMessage(error).show();
    }

    @Override
    protected void initView() {
        mBinding = (ActivityRetrofitBinding) dataBinding;
    }

    public void request(View v) {
        mPresenter.login();
    }
}
