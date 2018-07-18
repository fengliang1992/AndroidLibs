package com.fltry.androidlibs.sdkmodule.retrofit;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.sdkmodule.retrofit.base.MVPActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RetrofitActivity extends MVPActivity<ILoginView, LoginPresenter> implements ILoginView {

    public static final String BASE_URL = "http://192.168.100.109:8004";
    @BindView(R.id.retrofit_tv)
    TextView retrofitTv;
    @BindView(R.id.retrofit_btn)
    Button retrofitBtn;

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
        retrofitTv.setText("正在请求");
        retrofitTv.setTextColor(Color.GREEN);
        retrofitBtn.setClickable(false);
    }

    @Override
    public void dismissDialog() {
        retrofitTv.setText("请求结束");
        retrofitTv.setTextColor(Color.GRAY);
        retrofitBtn.setClickable(true);
    }

    @Override
    public void loginSucceed(String mse) {
        new AlertDialog.Builder(mContext).setMessage(mse).show();
    }

    @Override
    public void loginFailed(String error) {
        new AlertDialog.Builder(mContext).setMessage(error).show();
    }

    @OnClick({R.id.retrofit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.retrofit_btn:
                mPresenter.login();
                break;
        }
    }
}
