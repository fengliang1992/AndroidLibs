package com.fltry.androidlibs.view.netstate;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.databinding.ActivityNetStateBinding;
import com.fltry.module.lib_common.BaseActivity;

public class NetStateActivity extends BaseActivity<ActivityNetStateBinding> {

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

    }
}
