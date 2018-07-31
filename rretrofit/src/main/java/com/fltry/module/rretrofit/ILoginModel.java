package com.fltry.module.rretrofit;


import com.fltry.module.rretrofit.base.OnHttpCallBack;

public interface ILoginModel {

    void login(String userName, String password, String sign, String code, OnHttpCallBack<String> callBack);

}
