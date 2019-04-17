package com.fltry.androidlibs.view.netstate;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.view.View;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.databinding.ActivityNetStateBinding;
import com.fltry.module.lib_common.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NetStateActivity extends BaseActivity<ActivityNetStateBinding> {
    private NetWorkChangReceiver netWorkChangReceiver;
    private boolean isRegistered = false;
    String netState = "";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_state;
    }

    @Override
    protected String title() {
        return "监听网络状态";
    }

    @Override
    protected void initView() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        //注册网络状态监听广播
        netWorkChangReceiver = new NetWorkChangReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
        isRegistered = true;
    }

    public void cleanText(View v) {
        dataBinding.netStateTv.setText("");
        netState = "";
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void netStateChange(String state) {
        netState += getTime() + "：" + state+"\n";
        dataBinding.netStateTv.setText(netState);
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (isRegistered) {
            unregisterReceiver(netWorkChangReceiver);
        }
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
