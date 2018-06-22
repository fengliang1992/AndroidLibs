package com.fltry.androidlibs.sdkmodule.zxing;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;
import com.fltry.androidlibs.utils.toast.ToastUtil3;
import com.google.zxing.WriterException;

import butterknife.BindView;
import butterknife.OnClick;

public class ZxingActivity extends BaseActivity {

    @BindView(R.id.zxing_ed1)
    EditText zxingEd1;
    @BindView(R.id.zxing_iv2)
    ImageView zxingIv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing;
    }


    @OnClick({R.id.zxing_btn2, R.id.zxing_btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zxing_btn2:
                zxingEd1.setText("");
                break;
            case R.id.zxing_btn3:
                getToolbarTitle().setText("二维码");
                if (zxingEd1.getText().toString().equals("")) {
                    ToastUtil3.showLong(mContext, "请输入内容");
                    return;
                }
                try {
                    Bitmap qrCodeBitmap = EncodingHandler.createQRCode(zxingEd1.getText().toString(), 600);
                    zxingIv2.setImageBitmap(qrCodeBitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
