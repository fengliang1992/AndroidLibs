package com.fltry.module.database;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.fltry.module.database.databinding.ActivitySpBinding;
import com.fltry.module.lib_common.BaseActivity;

public class SPActivity extends BaseActivity {
    ActivitySpBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sp;
    }

    @Override
    protected String title() {
        return "SharedPreferences";
    }

    @Override
    protected void initView() {
        mBinding = (ActivitySpBinding) dataBinding;
    }

    public void saveOnClick(View v) {
        if (TextUtils.isEmpty(mBinding.spEt1.getText().toString())) {
            Toast.makeText(mContext, "请输入key", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mBinding.spEt2.getText().toString())) {
            Toast.makeText(mContext, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        SPUtils.put(mContext, mBinding.spEt1.getText().toString(), mBinding.spEt2.getText().toString());
    }

    public void readOnClick(View v) {
        if (TextUtils.isEmpty(mBinding.spEt4.getText().toString())) {
            Toast.makeText(mContext, "请输入key", Toast.LENGTH_SHORT).show();
            return;
        }
        String soGet = SPUtils.get(mContext, mBinding.spEt4.getText().toString(), "").toString();
        mBinding.spTv6.setText(soGet);
    }

}
