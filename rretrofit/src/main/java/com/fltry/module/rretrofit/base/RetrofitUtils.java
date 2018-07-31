package com.fltry.module.rretrofit.base;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {

    private static final int READ_TIMEOUT = 60;//读取超时时间  单位秒
    private static final int CONN_TIMEOUT = 12;//链接超时时间  单位秒
    private static Retrofit mRetrofit;

    public static Retrofit newInstance(String url) {
        mRetrofit = null;
        OkHttpClient okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient = builder.build();
        mRetrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())//增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create())//增加返回值为Gson的支持(以实体类返回)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//增加返回值为Oservable<T>的支持
                .build();
        return mRetrofit;
    }

}
