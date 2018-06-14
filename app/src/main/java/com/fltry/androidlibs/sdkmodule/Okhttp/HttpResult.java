package com.fltry.androidlibs.sdkmodule.Okhttp;

/**
 * Created by tol on 2018-04-02.
 * 网络请求结果类
 */
public class HttpResult {

    //请求结果
    private String result;
    //是否成功
    private boolean isSuccess;
    //响应事件
    private int responseEvent;

    public HttpResult(String result, boolean isSuccess, int responseEvent) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.responseEvent = responseEvent;
    }

    public String getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getResponseEvent() {
        return responseEvent;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "result='" + result + '\'' +
                ", isSuccess=" + isSuccess +
                ", responseEvent=" + responseEvent +
                '}';
    }
}
