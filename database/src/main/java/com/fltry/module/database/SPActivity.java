package com.fltry.module.database;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fltry.module.lib_common.BaseActivity;

public class SPActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 请输入key
     */
    private EditText mSpEt1;
    /**
     * 请输入需要保存的内容
     */
    private EditText mSpEt2;
    /**
     * 存储
     */
    private Button mSpBtn3;
    /**
     * 请输入key
     */
    private EditText mSpEt4;
    /**
     * 读取
     */
    private Button mSpBtn5;
    private TextView mSpTv6;

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
        mSpEt1 = (EditText) findViewById(R.id.sp_et1);
        mSpEt2 = (EditText) findViewById(R.id.sp_et2);
        mSpBtn3 = (Button) findViewById(R.id.sp_btn3);
        mSpBtn3.setOnClickListener(this);
        mSpEt4 = (EditText) findViewById(R.id.sp_et4);
        mSpBtn5 = (Button) findViewById(R.id.sp_btn5);
        mSpBtn5.setOnClickListener(this);
        mSpTv6 = (TextView) findViewById(R.id.sp_tv6);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sp_btn3) {
            if (TextUtils.isEmpty(mSpEt1.getText().toString())) {
                Toast.makeText(mContext, "请输入key", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mSpEt2.getText().toString())) {
                Toast.makeText(mContext, "请输入内容", Toast.LENGTH_SHORT).show();
                return;
            }
            SPUtils.put(mContext, mSpEt1.getText().toString(), mSpEt2.getText().toString());
        } else if (v.getId() == R.id.sp_btn5) {
            if (TextUtils.isEmpty(mSpEt4.getText().toString())) {
                Toast.makeText(mContext, "请输入key", Toast.LENGTH_SHORT).show();
                return;
            }
            String soGet = SPUtils.get(mContext, mSpEt4.getText().toString(), "").toString();
            mSpTv6.setText(soGet);
        }
    }
}
