package com.fltry.module.eventbus;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fltry.module.lib_common.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class OkHttpActivity extends BaseActivity implements View.OnClickListener {

    EditText okHttpEt;
    /**
     * 发送请求
     */
    private Button mOkHttpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("okHttp+EventBus");
        EventBus.getDefault().register(this);
        mOkHttpBtn = (Button) findViewById(R.id.okHttp_btn);
        mOkHttpBtn.setOnClickListener(this);
        okHttpEt = findViewById(R.id.okHttp_et);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.okHttp_btn){
            if (TextUtils.isEmpty(okHttpEt.getText().toString())) {
                Toast.makeText(mContext, "输入接口", Toast.LENGTH_LONG).show();
                return;
            }
            Http_Test_Get http_test_get = new Http_Test_Get("", okHttpEt.getText().toString());
            http_test_get.sendRequest();
        }
    }
}
