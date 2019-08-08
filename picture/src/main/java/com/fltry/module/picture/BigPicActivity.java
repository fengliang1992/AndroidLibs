package com.fltry.module.picture;

import android.webkit.WebSettings;

import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.picture.databinding.ActivityBigPicBinding;

public class BigPicActivity extends BaseActivity<ActivityBigPicBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_big_pic;
    }

    @Override
    protected void initView() {
        dataBinding.webView.getSettings().setSupportZoom(true);//缩放
        dataBinding.webView.getSettings().setBuiltInZoomControls(true);
        dataBinding.webView.getSettings().setDisplayZoomControls(false);//不显示控制器
        dataBinding.webView.getSettings().setUseWideViewPort(true);
        dataBinding.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        dataBinding.webView.getSettings().setLoadWithOverviewMode(true);
//        dataBinding.webView.loadUrl("file:///android_res/drawable/qingming.jpg");
        dataBinding.webView.loadUrl("file:///android_asset/qingming.jpg");
    }

    @Override
    protected String title() {
        return "webView加载大图";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBinding.webView.destroy();
    }

}

