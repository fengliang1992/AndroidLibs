package com.fltry.module.animation;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class CircleView extends View {
    Paint mPaint;
    int mRadius;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffff4848);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mRadius = Math.min(width, height);
        setMeasuredDimension(mRadius * 2, mRadius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(80);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(255);
        canvas.drawCircle(mRadius, mRadius, mRadius / 2, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(mRadius, mRadius, mRadius - 1, mPaint);
    }
}
