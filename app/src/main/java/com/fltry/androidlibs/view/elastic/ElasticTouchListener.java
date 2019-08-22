package com.fltry.androidlibs.view.elastic;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

public class ElasticTouchListener implements OnTouchListener {
    private View inner;
    View[] children;
    private float mY;
    private Rect normal = new Rect();
    private boolean animationFinish = true;
    private int[] tops;
    private int[] bottoms;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        if (inner == null && children == null) {
            if (v instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) v;
                int count = group.getChildCount();
                if (count > 0) {
                    children = new View[count];
                    tops = new int[count];
                    bottoms = new int[count];
                    for (int i = 0; i < count; i++) {
                        children[i] = group.getChildAt(i);
                        tops[i] = children[i].getTop();
                        bottoms[i] = children[i].getBottom();
                    }
                }
            }
            inner = v;
        }
        if (animationFinish && (inner != null || children != null)) {
            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mY = ev.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    mY = 0;
                    if (isNeedAnimation()) {
                        animation();
                    }
                    inner.invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float preY = mY == 0 ? ev.getY() : mY;
                    float nowY = ev.getY();
                    int deltaY = (int) (preY - nowY);
                    mY = nowY;

                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
                    }
                    if (children != null) {
                        View view = null;
                        for (int i = 0; i < children.length; i++) {
                            view = children[i];
                            view.layout(view.getLeft(), view.getTop() - deltaY / 2, view.getRight(), view.getBottom() - deltaY / 2);
                        }
                    } else {
                        inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2, inner.getRight(), inner.getBottom() - deltaY / 2);
                    }

                    inner.invalidate();
                    break;
                default:
                    break;
            }
        } else {
            return false;
        }
        return true;
    }


    public void animation() {
        if (children == null) {
            TranslateAnimation trans = new TranslateAnimation(0, 0, 0, normal.top - inner.getTop());
            trans.setDuration(200);
            trans.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    animationFinish = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    inner.clearAnimation();
                    inner.layout(normal.left, normal.top, normal.right, normal.bottom);
                    normal.setEmpty();
                    animationFinish = true;
                }
            });
            inner.startAnimation(trans);
        } else {
            for (int i = 0; i < children.length; i++) {
                final View view = children[i];
                if (view.getVisibility() == View.VISIBLE) {
                    final int index = i;
                    TranslateAnimation trans = new TranslateAnimation(0, 0, 0, tops[i] - view.getTop());
                    trans.setDuration(200);
                    trans.setAnimationListener(new AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            animationFinish = false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            view.clearAnimation();
                            view.layout(view.getLeft(), tops[index], view.getRight(), bottoms[index]);
                            normal.setEmpty();
                            animationFinish = true;
                        }
                    });
                    view.startAnimation(trans);
                }
            }
        }

    }


    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }
}
