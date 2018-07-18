package com.fltry.androidlibs.sdkmodule.Okhttp;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tol on 2018-04-25.
 */

public class MyHttpUtils {

    /**
     * 网络请求 post
     */
    public static void setHttpPostRequest(String url, RequestBody requestBody, String header, OkHttpClient okHttpClient
            , int responseEvent) {
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", header)
                .post(requestBody)
                .build();
        httpCallBack(request, okHttpClient, responseEvent);
    }

    /**
     * 网络请求 get
     */
    public static void httpGetRequest(String url, String header, OkHttpClient okHttpClient
            , int responseEvent) {
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", header)
                .build();
        httpCallBack(request, okHttpClient, responseEvent);
    }

    /**
     * 网络请求 put
     */
    public static void setHttpPutRequest(String url, String header, RequestBody requestBody, OkHttpClient okHttpClient
            , int responseEvent) {
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .addHeader("Authorization", header)
                .build();
        httpCallBack(request, okHttpClient, responseEvent);
    }

    public static void httpCallBack(Request request, OkHttpClient okHttpClient, final int responseEvent) {
        //创建Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                EventBus.getDefault().post(new HttpFaile(e.getMessage(), responseEvent));
            }

            //网络请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                EventBus.getDefault().post(new HttpResult(result, true, responseEvent));
            }
        });
    }

}
