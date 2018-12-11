package com.fltry.module.apk;

import com.fltry.module.apk.databinding.ActivityApkBinding;
import com.fltry.module.lib_common.BaseActivity;

public class ApkActivity extends BaseActivity<ActivityApkBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apk;
    }

    @Override
    protected String title() {
        return "打包apk";
    }

    @Override
    protected void initView() {
        String text = AppUtils.getAppName(mContext) + "\n" + AppUtils.getPackageName(mContext) + "\n" +
                AppUtils.getVersionName(mContext) + "\n" + AppUtils.getVersionCode(mContext);
        dataBinding.setText(text);
        dataBinding.apkIv.setImageBitmap(AppUtils.getBitmap(mContext));
    }
}
