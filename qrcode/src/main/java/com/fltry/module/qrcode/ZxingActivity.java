package com.fltry.module.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fltry.module.lib_common.BaseActivity;
import com.google.zxing.WriterException;


public class ZxingActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 输入二维码内容
     */
    private EditText mZxingEd1;
    /**
     * 清除
     */
    private Button mZxingBtn2;
    private ImageView mZxingIv2;
    /**
     * 生成二维码
     */
    private Button mZxingBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("二维码");
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing;
    }


    private void initView() {
        mZxingEd1 = (EditText) findViewById(R.id.zxing_ed1);
        mZxingBtn2 = (Button) findViewById(R.id.zxing_btn2);
        mZxingBtn2.setOnClickListener(this);
        mZxingIv2 = (ImageView) findViewById(R.id.zxing_iv2);
        mZxingBtn3 = (Button) findViewById(R.id.zxing_btn3);
        mZxingBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.zxing_btn2) {
            mZxingEd1.setText("");
        }else if (v.getId() == R.id.zxing_btn3) {
            if (mZxingEd1.getText().toString().equals("")) {
                Toast.makeText(mContext, "请输入内容",Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Bitmap qrCodeBitmap = EncodingHandler.createQRCode(mZxingEd1.getText().toString(), 600);
                mZxingIv2.setImageBitmap(qrCodeBitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}
