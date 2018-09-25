package com.fltry.module.glide;


public interface IGlideView {

    void httpSucceed(Movice movice);

    void httpFailed(String error);

    String getCount();

}
