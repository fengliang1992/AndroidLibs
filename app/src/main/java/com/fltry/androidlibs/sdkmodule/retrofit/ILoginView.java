package com.fltry.androidlibs.sdkmodule.retrofit;


public interface ILoginView {

    void showDialog();

    void dismissDialog();

    void loginSucceed(String mse);

    void loginFailed(String error);

}
