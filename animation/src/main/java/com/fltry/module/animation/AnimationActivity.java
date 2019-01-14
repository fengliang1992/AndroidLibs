package com.fltry.module.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import com.fltry.module.animation.databinding.ActivityAnimationBinding;
import com.fltry.module.lib_common.BaseActivity;

public class AnimationActivity extends BaseActivity<ActivityAnimationBinding> implements View.OnClickListener {

    boolean rotation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected String title() {
        return "动画";
    }

    @Override
    protected void initView() {
        dataBinding.btnRotate.setOnClickListener(this);
        dataBinding.btnTranslate.setOnClickListener(this);
        dataBinding.btnScale.setOnClickListener(this);
        dataBinding.btnAlpha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rotate:
                rotate();
                break;
            case R.id.btn_translate:
                translate();
                break;
            case R.id.btn_scale:
                scale();
                break;
            case R.id.btn_alpha:
                alpha();
                break;
        }
    }

    private void scale() {
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(dataBinding.view, "scaleX", 1.0f, 3.0f, 1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(dataBinding.view, "scaleY", 1.0f, 3.0f, 1.0f);
        animationSet.play(animator).with(animator2);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.setDuration(500);
        animationSet.start();
    }

    private void rotate() {
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator animator;
        if (rotation)
            animator = ObjectAnimator.ofFloat(dataBinding.view, "rotationY", 0, 360);
        else
            animator = ObjectAnimator.ofFloat(dataBinding.view, "rotationX", 0, 360);
        animationSet.play(animator);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.setDuration(500);
        animationSet.start();
        rotation = !rotation;
    }

    private void translate() {
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(dataBinding.view, "translationX", 0, 200, 0, -200, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(dataBinding.view, "translationY", 0, 200, 0, -200, 0);
        animationSet.play(animator).with(animator2);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.setDuration(1000);
        animationSet.start();
    }

    private void alpha() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(dataBinding.view, "alpha", 1.0f, 0.0f, 1.0f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.start();
    }
}
