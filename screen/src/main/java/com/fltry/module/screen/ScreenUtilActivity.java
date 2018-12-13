package com.fltry.module.screen;

import android.graphics.Bitmap;
import android.view.View;

import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.screen.databinding.ActivityScreenUtilBinding;

import java.util.Arrays;


public class ScreenUtilActivity extends BaseActivity<ActivityScreenUtilBinding> {

    @Override
    protected void initView() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("屏幕宽度：" + ScreenUtils.getScreenWidth(mContext) + "\n");
        stringBuffer.append("屏幕高度：" + ScreenUtils.getScreenHeight(mContext) + "\n");
        stringBuffer.append("状态栏的高度1：" + ScreenUtils.getStatusHeight(mContext) + "\n");
        stringBuffer.append("状态栏的高度2：" + ScreenUtils.getStatusBarHeight(mContext));
        stringBuffer.append("当前手机系统语言：" + SystemUtil.getSystemLanguage() + "\n");
        stringBuffer.append("当前手机系统版本号：" + SystemUtil.getSystemVersion() + "\n");
        stringBuffer.append("手机型号：" + SystemUtil.getSystemModel() + "\n");
        stringBuffer.append("手机厂商：" + SystemUtil.getDeviceBrand() + "\n");
        stringBuffer.append("手机IMEI：" + SystemUtil.getIMEI(mContext) + "\n");
        stringBuffer.append("当前系统上的语言列表：" + Arrays.toString(SystemUtil.getSystemLanguageList()) + "\n");
        dataBinding.suTv.setText(stringBuffer.toString());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_screen_util;
    }

    @Override
    protected String title() {
        return "屏幕属性";
    }


    public void getPic(View v) {
        Bitmap bitmap = ScreenUtils.snapShotWithStatusBar(ScreenUtilActivity.this);
        dataBinding.suIv2.setImageBitmap(bitmap);
    }

    public void getPic2(View v) {
        Bitmap bitmap2 = ScreenUtils.snapShotWithoutStatusBar(ScreenUtilActivity.this);
        dataBinding.suIv2.setImageBitmap(bitmap2);
    }
}
