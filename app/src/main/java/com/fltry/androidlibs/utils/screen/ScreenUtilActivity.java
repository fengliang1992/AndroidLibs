package com.fltry.androidlibs.utils.screen;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ScreenUtilActivity extends BaseActivity {

    @BindView(R.id.su_tv)
    TextView suTv;
    @BindView(R.id.su_iv2)
    ImageView suIv2;
    @BindView(R.id.su_iv3)
    ImageView suIv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("屏幕属性");

        initView();
    }

    private void initView() {
        String screenWidth = "屏幕宽度：" + ScreenUtils.getScreenWidth(getMContext()) + "\n";
        String screenHeight = "屏幕高度：" + ScreenUtils.getScreenHeight(getMContext()) + "\n";
        String statusHeight = "状态栏的高度1：" + ScreenUtils.getStatusHeight(getMContext()) + "\n";
        String statusBarHeight = "状态栏的高度2：" + ScreenUtils.getStatusBarHeight(getMContext());
        suTv.setText(screenWidth + screenHeight + statusHeight + statusBarHeight);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_screen_util;
    }

    @OnClick({R.id.su_iv2, R.id.su_iv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.su_iv2:
                Bitmap bitmap = ScreenUtils.snapShotWithStatusBar(ScreenUtilActivity.this);
                suIv2.setImageBitmap(bitmap);
                break;
            case R.id.su_iv3:
                Bitmap bitmap2 = ScreenUtils.snapShotWithoutStatusBar(ScreenUtilActivity.this);
                suIv3.setImageBitmap(bitmap2);
                break;
        }
    }
}
