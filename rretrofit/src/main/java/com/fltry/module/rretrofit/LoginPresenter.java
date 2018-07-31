package com.fltry.module.rretrofit;

import com.fltry.module.rretrofit.base.BasePresenter;
import com.fltry.module.rretrofit.base.OnHttpCallBack;

public class LoginPresenter extends BasePresenter<ILoginView> {

    ILoginModelImp loginImp = new ILoginModelImp();

    public void login() {
        getView().showDialog();
        loginImp.login("admin",
                "MTIzNTQ2IQ==",
                "123456",
                "5xc5",
                new OnHttpCallBack<String>() {
                    @Override
                    public void success(String data) {
                        getView().dismissDialog();
                        getView().loginSucceed(data);
                    }

                    @Override
                    public void failed(String error) {
                        getView().dismissDialog();
                        getView().loginFailed("登录失败：" + error);
                    }
                });
    }


}
