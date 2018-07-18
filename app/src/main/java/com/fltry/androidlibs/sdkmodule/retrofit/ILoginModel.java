package com.fltry.androidlibs.sdkmodule.retrofit;


import com.fltry.androidlibs.sdkmodule.retrofit.base.OnHttpCallBack;

public interface ILoginModel {

    void login(String userName, String password, String sign, String code, OnHttpCallBack<String> callBack);

}
