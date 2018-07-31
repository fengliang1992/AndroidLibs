package com.fltry.module.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;

public class LoadLinearLayout extends LinearLayout {
    private Context mContext;
    TextView titleTv;
    TextView result;
    SpinKitView loading;

    public static final int STATE_INIT = 0;
    public static final int STATE_SUCCEED = 1;
    public static final int STATE_FAILED = 2;

    public LoadLinearLayout(Context context) {
        super(context);
        initView(context);
    }

    public LoadLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_load_layout, this, true);
        titleTv = view.findViewById(R.id.view_load_layout_tv1);
        result = view.findViewById(R.id.view_load_layout_tv2);
        loading = view.findViewById(R.id.view_load_layout_sv2);
    }

    public void setReadState(int state) {
        switch (state) {
            case STATE_INIT:
                result.setText("");
                loading.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCEED:
                result.setText("成功");
                loading.setVisibility(View.GONE);
                result.setTextColor(Color.GREEN);
                break;
            case STATE_FAILED:
                result.setText("失败");
                result.setTextColor(Color.RED);
                loading.setVisibility(View.GONE);
                break;
        }
    }

    public void setTitile(String title) {
        titleTv.setText(title);
    }
}
