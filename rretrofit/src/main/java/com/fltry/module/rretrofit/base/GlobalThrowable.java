package com.fltry.module.rretrofit.base;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

public class GlobalThrowable {
    public static String globalThrowable(Throwable e){
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            if (code == 500 || code == 404) {
                return "服务器出错";
            }
        } else if (e instanceof ConnectException) {
            return "网络断开,请打开网络!";
        } else if (e instanceof SocketTimeoutException) {
            return "网络连接超时!!";
        } else if (e instanceof UnknownHostException) {
            return "网络断开,请打开网络!";
        } else {
            return "发生未知错误";
        }
        return null;
    }
}

