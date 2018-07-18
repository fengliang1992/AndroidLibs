package com.fltry.androidlibs.sdkmodule.retrofit;

import com.fltry.androidlibs.sdkmodule.Okhttp.Http_Test_Get;
import com.fltry.androidlibs.sdkmodule.retrofit.base.BasePresenter;

/**
 * Created by tol on 2018-06-26.
 */

public class LoginPresenter extends BasePresenter<IResult> {

    ILoginImp loginImp = new ILoginImp();

    public void login(){
        loginImp.login();

    }

}
