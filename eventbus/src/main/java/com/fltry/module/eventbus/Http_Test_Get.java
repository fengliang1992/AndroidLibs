package com.fltry.module.eventbus;

import okhttp3.RequestBody;

public class Http_Test_Get extends BaseHttpRequest {

    private String url;

    public Http_Test_Get(String header, String url) {
        super(header);
        this.url = url;
    }

    @Override
    public int getResponseEvent() {
        return 0;
    }

    @Override
    public int getRequestMothed() {
        return GET_REQUEST;
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
