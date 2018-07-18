package com.fltry.androidlibs.sdkmodule.retrofit.base;

import io.reactivex.Observable;
import okhttp3.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by LNN on 2017/8/15.
 * retrofit方法声明接口
 */

public interface APIService {

    /**
     * 用户登录的接口
     *
     * @return RxJava 对象
     */
    @POST("/accounts/authen")
    @FormUrlEncoded
    Observable<String> getLogin(@Field("loginname") String loginname,
                                @Field("pwd") String pwd,
                                @Field("sign") String sign,
                                @Field("code") String code);
}
