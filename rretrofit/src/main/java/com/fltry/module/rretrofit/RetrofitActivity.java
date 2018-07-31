package com.fltry.module.rretrofit;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fltry.module.rretrofit.base.MVPActivity;


public class RetrofitActivity extends MVPActivity<ILoginView, LoginPresenter> implements ILoginView, View.OnClickListener {
    public static final String BASE_URL = "http://192.168.100.109:8004";
    /**
     * 登录（无异常）
     */
    private Button mRetrofitBtn;
    private TextView mRetrofitTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("Retrofit+rxandroid+MVP");
        initView();
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
        mRetrofitTv.setText("正在请求");
        mRetrofitTv.setTextColor(Color.GREEN);
        mRetrofitBtn.setClickable(false);
    }

    @Override
    public void dismissDialog() {
        mRetrofitTv.setText("请求结束");
        mRetrofitTv.setTextColor(Color.GRAY);
        mRetrofitBtn.setClickable(true);
    }

    @Override
    public void loginSucceed(String mse) {
        new AlertDialog.Builder(mContext).setMessage(mse).show();
    }

    @Override
    public void loginFailed(String error) {
        new AlertDialog.Builder(mContext).setMessage(error).show();
    }

    private void initView() {
        mRetrofitBtn = (Button) findViewById(R.id.retrofit_btn);
        mRetrofitBtn.setOnClickListener(this);
        mRetrofitTv = (TextView) findViewById(R.id.retrofit_tv);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.retrofit_btn){
            mPresenter.login();
        }
    }
}
