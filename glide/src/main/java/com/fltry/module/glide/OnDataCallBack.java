package com.fltry.module.glide;

public interface OnDataCallBack<T> {

    void succeed(T data);

    void failed(String error);

}
