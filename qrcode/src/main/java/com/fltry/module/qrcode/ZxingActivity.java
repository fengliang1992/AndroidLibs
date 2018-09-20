package com.fltry.module.qrcode;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.qrcode.databinding.ActivityZxingBinding;
import com.google.zxing.WriterException;


public class ZxingActivity extends BaseActivity {

    ActivityZxingBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing;
    }

    @Override
    protected String title() {
        return "二维码";
    }

    @Override
    protected void initView() {
        mBinding = (ActivityZxingBinding) dataBinding;
    }

    public void clearPic(View v) {
        mBinding.zxingEd1.setText("");
    }

    public void createPic(View v) {
        if (mBinding.zxingEd1.getText().toString().equals("")) {
            Toast.makeText(mContext, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Bitmap qrCodeBitmap = EncodingHandler.createQRCode(mBinding.zxingEd1.getText().toString(), 600);
            mBinding.zxingIv2.setImageBitmap(qrCodeBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
