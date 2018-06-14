package com.fltry.androidlibs.sdkmodule.Okhttp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class OkHttpActivity extends BaseActivity {

    @BindView(R.id.okHttp_et)
    EditText okHttpEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("okHttp网络框架");
        EventBus.getDefault().register(this);
        okHttpEt.setText("http://10.10.10.107:8004/areas");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_http;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void httpSccessEvent(HttpResult httpResult) {
        new AlertDialog.Builder(mContext).setMessage(httpResult.getResult()).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void httpFaileEvent(HttpFaile httpFaile) {
        new AlertDialog.Builder(mContext).setMessage("请求失败：" + httpFaile.getFalieMsg()).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @OnClick(R.id.okHttp_btn)
    public void onViewClicked() {
        if (TextUtils.isEmpty(okHttpEt.getText().toString())){
            Toast.makeText(mContext,"输入接口",Toast.LENGTH_LONG).show();
            return;
        }
        Http_Test_Get http_test_get = new Http_Test_Get("",okHttpEt.getText().toString());
        http_test_get.sendRequest();
    }
}
