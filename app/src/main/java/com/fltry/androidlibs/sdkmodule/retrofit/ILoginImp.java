package com.fltry.androidlibs.sdkmodule.retrofit;

import com.fltry.androidlibs.sdkmodule.Okhttp.Http_Test_Get;

/**
 * Created by tol on 2018-06-26.
 */

public class ILoginImp implements ILogin {

    @Override
    public void login(Http_Test_Get testGet) {
        RetrofitUtils.newInstance("http://10.10.10.107:8004")
                .create(APIService.class)
                .getLogin();
    }
}
