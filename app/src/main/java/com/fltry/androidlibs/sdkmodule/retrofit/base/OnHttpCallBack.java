package com.fltry.androidlibs.sdkmodule.retrofit.base;

public interface OnHttpCallBack<T> {

    void success(T data);

    void failed(String error);

}
