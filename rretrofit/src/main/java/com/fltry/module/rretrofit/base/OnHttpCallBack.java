package com.fltry.module.rretrofit.base;

public interface OnHttpCallBack<T> {

    void success(T data);

    void failed(String error);

}
