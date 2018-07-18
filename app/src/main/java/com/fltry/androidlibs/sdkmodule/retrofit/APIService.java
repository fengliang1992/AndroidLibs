package com.fltry.androidlibs.sdkmodule.retrofit;

import android.database.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by LNN on 2017/8/15.
 * retrofit方法声明接口
 */

public interface APIService {

    /**
     * 用户登录的接口
     * @return RxJava 对象
     */
    @Headers("Accept:application/json")
    @GET("/areas")
    Call<ResponseBody> getResult();

    /**
     * 用户登录的接口
     * @return RxJava 对象
     */
    @Headers("Accept:application/json")
    @GET("/areas")
    Call<ResponseBody> getLogin();

}
