package com.fltry.module.screen;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fltry.module.lib_common.BaseActivity;


public class ScreenUtilActivity extends BaseActivity implements View.OnClickListener {
    private TextView mSuTv;
    private ImageView mSuIv2;
    private ImageView mSuIv3;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("屏幕属性");

        initView();
    }

    private void initView() {
        mSuTv = (TextView) findViewById(R.id.su_tv);
        mSuIv2 = (ImageView) findViewById(R.id.su_iv2);
        mSuIv2.setOnClickListener(this);
        mSuIv3 = (ImageView) findViewById(R.id.su_iv3);
        mSuIv3.setOnClickListener(this);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("屏幕宽度：" + ScreenUtils.getScreenWidth(mContext) + "\n");
        stringBuffer.append("屏幕高度：" + ScreenUtils.getScreenHeight(mContext) + "\n");
        stringBuffer.append("状态栏的高度1：" + ScreenUtils.getStatusHeight(mContext) + "\n");
        stringBuffer.append("状态栏的高度2：" + ScreenUtils.getStatusBarHeight(mContext));
        mSuTv.setText(stringBuffer.toString());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_screen_util;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.su_iv2) {
            Bitmap bitmap = ScreenUtils.snapShotWithStatusBar(ScreenUtilActivity.this);
            mSuIv2.setImageBitmap(bitmap);
        } else if (v.getId() == R.id.su_iv3) {
            Bitmap bitmap2 = ScreenUtils.snapShotWithoutStatusBar(ScreenUtilActivity.this);
            mSuIv3.setImageBitmap(bitmap2);
        }
    }
}
