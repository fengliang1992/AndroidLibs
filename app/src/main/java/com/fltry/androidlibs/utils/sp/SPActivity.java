package com.fltry.androidlibs.utils.sp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;
import com.fltry.androidlibs.utils.toast.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SPActivity extends BaseActivity {

    @BindView(R.id.sp_et1)
    EditText spEt1;
    @BindView(R.id.sp_et2)
    EditText spEt2;
    @BindView(R.id.sp_et4)
    EditText spEt4;
    @BindView(R.id.sp_tv6)
    TextView spTv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("SharedPreferences");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sp;
    }

    @OnClick({R.id.sp_btn3, R.id.sp_btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sp_btn3:
                if (TextUtils.isEmpty(spEt1.getText().toString())) {
                    ToastUtil.show(mContext, "请输入key");
                    return;
                }
                if (TextUtils.isEmpty(spEt2.getText().toString())) {
                    ToastUtil.show(mContext, "请输入内容");
                    return;
                }
                SPUtils.put(mContext, spEt1.getText().toString(), spEt2.getText().toString());
                break;
            case R.id.sp_btn5:
                if (TextUtils.isEmpty(spEt4.getText().toString())) {
                    ToastUtil.show(mContext, "请输入key");
                    return;
                }
                String soGet = SPUtils.get(mContext, spEt4.getText().toString(), "").toString();
                spTv6.setText(soGet);
                break;
        }
    }
}
