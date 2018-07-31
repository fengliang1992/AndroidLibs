package com.fltry.module.rretrofit.base;

import java.lang.ref.WeakReference;

public class BasePresenter<T> {

    private WeakReference<T> mViewReference;

    public void attachView(T view) {
        mViewReference = new WeakReference<T>(view);
    }

    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    /**
     * 提供获取与当前Presenter相关联的view引用的方法
     *
     * @return
     */
    protected T getView() {
        return mViewReference.get();
    }

}
