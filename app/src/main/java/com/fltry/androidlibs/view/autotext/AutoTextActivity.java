package com.fltry.androidlibs.view.autotext;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;
import com.fltry.androidlibs.utils.screen.ScreenUtils;

import butterknife.BindView;

public class AutoTextActivity extends BaseActivity {

    @BindView(R.id.auto_tv)
    AutoTextView autoTv;
    @BindView(R.id.auto_tv2)
    TextView autoTv2;
    @BindView(R.id.auto_tv3)
    TextView autoTv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("富文本");

        initView();
    }

    private void initView() {
        autoTv.setText("冯亮是帅哥冯亮是帅哥冯亮是帅哥冯亮是帅哥冯亮是帅哥冯亮是帅哥冯亮是帅哥冯亮是帅哥冯亮是帅哥");
        String text = "改变颜色，改变大小，冯亮是帅哥冯亮是帅哥冯亮是帅哥---SpannableStringBuilder";

        /* 方法一 */
        SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        spannableStringBuilder1.setSpan(foregroundColorSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(ScreenUtils.dip2px(mContext, 30));
        spannableStringBuilder1.setSpan(absoluteSizeSpan, 5, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        autoTv2.setText(spannableStringBuilder1);

        /* 方法二 */
        String text2 = "<font color='#ff4848'>改变颜色</font>" + "，改变大小暂时未研究出来---Html.fromHtml(text2)";
        autoTv3.setText(Html.fromHtml(text2));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auto_text;
    }
}
