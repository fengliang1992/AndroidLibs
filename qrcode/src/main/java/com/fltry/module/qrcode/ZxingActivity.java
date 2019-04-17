package com.fltry.module.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.fltry.module.lib_common.AlertDialogUtils;
import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.qrcode.databinding.ActivityZxingBinding;
import com.google.zxing.WriterException;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;


public class ZxingActivity extends BaseActivity<ActivityZxingBinding> {
    public static final int REQUEST_CODE_SCAN = 200;
    final static int MY_PERMISSIONS_REQUEST_PHOTO = 2;

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
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this
                    , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_PHOTO);
                return;
            }
        }
        dataBinding.zxingBtn4.setOnClickListener(v -> {
            Intent intent = new Intent(ZxingActivity.this, CaptureActivity.class);
            /*ZxingConfig是配置类
            *可以设置是否显示底部布局，闪光灯，相册，
            * 是否播放提示音  震动
            * 设置扫描框颜色等
            * 也可以不传这个参数
            * */
            ZxingConfig config = new ZxingConfig();
            config.setPlayBeep(true);//是否播放扫描声音 默认为true
            config.setShake(true);//是否震动  默认为true
            config.setDecodeBarCode(true);//是否扫描条形码 默认为true
            config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
            config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
            config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
            config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
            intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
            startActivityForResult(intent, REQUEST_CODE_SCAN);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra("codedContent");
                AlertDialogUtils.getMyAlert(mContext, "", content).show();
            }
        }

    }


    public void clearPic(View v) {
        dataBinding.zxingEd1.setText("");
    }

    public void createPic(View v) {
        if (dataBinding.zxingEd1.getText().toString().equals("")) {
            Toast.makeText(mContext, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Bitmap qrCodeBitmap = EncodingHandler.createQRCode(dataBinding.zxingEd1.getText().toString(), 600);
            dataBinding.zxingIv2.setImageBitmap(qrCodeBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
