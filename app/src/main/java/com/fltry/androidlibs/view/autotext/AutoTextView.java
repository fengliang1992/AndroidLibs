package com.fltry.androidlibs.view.autotext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class AutoTextView extends AppCompatTextView {

    public static final String TAG = "AutoTextView";

    /**
     * 字幕滚动的速度 快，普通，慢
     */
    public static final int SCROLL_SLOW = 0;
    public static final int SCROLL_NORM = 1;
    public static final int SCROLL_FAST = 2;

    /**
     * 字幕内容
     */
    private String mText;

    /**
     * 字幕字体大小
     */
    private float mTextSize;

    private float offX = 0f;

    private float mStep = 2.0f;

    private Rect mRect = new Rect();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Shader mShaderA;

    public AutoTextView(Context context) {
        this(context, null);
    }

    public AutoTextView(Context context, AttributeSet attr) {
        super(context, attr);
        setSingleLine(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //变色关键代码
        mShaderA = new LinearGradient(0, 300, getMeasuredWidth(), -300,
                new int[]{Color.parseColor("#005a5a5a"),
                        Color.parseColor("#5a5a5a"),
                        Color.BLACK,
                        Color.RED,
                        Color.RED,
                        Color.BLACK,
                        Color.YELLOW,
                        Color.BLUE
                },
                //设置横向距离
                new float[]{0, 0.2f, 0.21f, 0.31f, 0.499f, 0.5f, 0.7f, 0.8f}, Shader.TileMode.CLAMP);
        mPaint.setShader(mShaderA);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mText = getText().toString();
        mTextSize = getTextSize();
        mPaint.setColor(getCurrentTextColor());
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mText, 0, mText.length(), mRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x, y;
        x = getMeasuredWidth() - offX;
        y = getMeasuredHeight() / 2 + (mPaint.descent() - mPaint.ascent()) / 2;
        canvas.drawText(mText, x, y, mPaint);
        offX += mStep;
        if (offX >= getMeasuredWidth() + mRect.width()) {
            offX = 0f;
        }
        invalidate();
    }

}
