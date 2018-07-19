package com.fltry.androidlibs.utils.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by yupu on 2016/6/30-15:05.
 * Email:459112332@qq.com
 */
public class LargeView extends View {
    private GestureDetector mGestureDetector;
    private BitmapRegionDecoder bitmapRegionDecoder;
    private Rect mRect = new Rect();
    private int bitmapWidth;
    private int bitmapHeight;
    private Bitmap bitmapDraw;
    private BitmapFactory.Options mOptions = new BitmapFactory.Options();;
    private int screenWidth, screenHeight;

    public LargeView(Context context) {
        this(context, null);
    }

    public LargeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LargeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        try {
            mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            InputStream inputStream = getResources().getAssets().open("qingming.jpg");

            mGestureDetector = new GestureDetector(context, new MGestureListener());
            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            bitmapWidth = options.outWidth;
            bitmapHeight = options.outHeight;

            WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            screenWidth = metrics.widthPixels;
            screenHeight = metrics.heightPixels;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (bitmapDraw != null) {
            bitmapDraw.recycle();
            bitmapDraw = null;
        }
        if (bitmapRegionDecoder != null) {
            bitmapRegionDecoder.recycle();
            bitmapRegionDecoder = null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width > bitmapWidth) {
            width = bitmapWidth;
        }

        if (height > bitmapHeight) {
            height = bitmapHeight;
        }

        int imageWidth = bitmapWidth;
        int imageHeight = bitmapHeight;

        mRect.left = imageWidth / 2 - width / 2;
        mRect.top = imageHeight / 2 - height / 2;
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;
        setMeasuredDimension(width, height);
    }

    public void setPhoto(int offY) {
        try {
            InputStream inputStream = getContext().getResources().getAssets().open("qingming.jpg");
            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            bitmapWidth = options.outWidth;
            bitmapHeight = options.outHeight;

            int width = screenWidth;
            int height = screenHeight - offY;

            if (width > bitmapWidth) {
                width = bitmapWidth;
            }

            if (height > bitmapHeight) {
                height = bitmapHeight;
            }

            int imageWidth = bitmapWidth;
            int imageHeight = bitmapHeight;

            mRect.left = imageWidth / 2 - width / 2;
            mRect.top = imageHeight / 2 - height / 2;
            mRect.right = mRect.left + width;
            mRect.bottom = mRect.top + height;

            setLayoutParams(new LinearLayout.LayoutParams(width, height));
            //setMeasuredDimension(width, height);
            invalidate();
        } catch (IOException e) {
        }
    }

    public void start() {
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmapDraw = bitmapRegionDecoder.decodeRegion(mRect, mOptions);
        canvas.drawBitmap(bitmapDraw, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    class MGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float left = mRect.left + distanceX;
            left = left < 0 ? 0 : left;
            float right = mRect.right + distanceX;
            right = right > bitmapWidth ? bitmapWidth : right;
            float top = mRect.top + distanceY;
            top = top < 0 ? 0 : top;
            float bottom = mRect.bottom + distanceY;
            bottom = bottom > bitmapHeight ? bitmapHeight : bottom;
            if (left == 0) {
                right = getMeasuredWidth();
            }
            if (right == bitmapWidth) {
                left = bitmapWidth - getMeasuredWidth();
            }
            if (top == 0) {
                bottom = getMeasuredHeight();
            }
            if (bottom == bitmapHeight) {
                top = bitmapHeight - getMeasuredHeight();
            }
            mRect.left = (int) left;
            mRect.right = (int) right;
            mRect.top = (int) top;
            mRect.bottom = (int) bottom;
            invalidate();
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }
    }
}
