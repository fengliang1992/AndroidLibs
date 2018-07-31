package com.fltry.module.eventbus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by tol on 2018-04-25.
 * http请求
 */
public abstract class BaseHttpRequest {

    public String header;

    public static final int GET_REQUEST = 1;
    public static final int POST_REQUEST = 2;
    public static final int PUT_REQUEST = 3;
    public static final int GET_REQUEST2 = 4;

    public abstract int getResponseEvent();

    public abstract int getRequestMothed();

    public abstract RequestBody getRequestBody();

    public abstract String getUrl();

    public BaseHttpRequest(String header) {
        this.header = header;
    }


    public void sendRequest() {
        switch (getRequestMothed()) {
            case GET_REQUEST:
                MyHttpUtils.httpGetRequest(getUrl(), header, new OkHttpClient(), getResponseEvent());
                break;
            case POST_REQUEST:
                MyHttpUtils.setHttpPostRequest(getUrl(), getRequestBody(), header, new OkHttpClient(), getResponseEvent());
                break;
            case PUT_REQUEST:
                MyHttpUtils.setHttpPutRequest(getUrl(), header, getRequestBody(), new OkHttpClient(), getResponseEvent());
                break;
            case GET_REQUEST2:
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(12, TimeUnit.SECONDS);
                builder.readTimeout(300, TimeUnit.SECONDS);
                MyHttpUtils.httpGetRequest(getUrl(), header, builder.build(), getResponseEvent());
                break;
        }
    }
}
