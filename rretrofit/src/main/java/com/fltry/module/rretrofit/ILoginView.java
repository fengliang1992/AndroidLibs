package com.fltry.module.rretrofit;


public interface ILoginView {

    void showDialog();

    void dismissDialog();

    void loginSucceed(String mse);

    void loginFailed(String error);

}
