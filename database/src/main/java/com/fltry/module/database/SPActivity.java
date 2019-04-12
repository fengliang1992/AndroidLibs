package com.fltry.module.database;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.fltry.module.database.databinding.ActivitySpBinding;
import com.fltry.module.lib_common.BaseActivity;

public class SPActivity extends BaseActivity<ActivitySpBinding> {

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
        dataBinding.spDbBtn.setOnClickListener(v -> startActivity(new Intent(mContext, DBActivity.class)));
    }

    public void saveOnClick(View v) {
        if (TextUtils.isEmpty(dataBinding.spEt1.getText().toString())) {
            Toast.makeText(mContext, "请输入key", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(dataBinding.spEt2.getText().toString())) {
            Toast.makeText(mContext, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        SPUtils.put(mContext, dataBinding.spEt1.getText().toString(), dataBinding.spEt2.getText().toString());
    }

    public void readOnClick(View v) {
        if (TextUtils.isEmpty(dataBinding.spEt4.getText().toString())) {
            Toast.makeText(mContext, "请输入key", Toast.LENGTH_SHORT).show();
            return;
        }
        String soGet = SPUtils.get(mContext, dataBinding.spEt4.getText().toString(), "").toString();
        dataBinding.spTv6.setText(soGet);
    }

}
