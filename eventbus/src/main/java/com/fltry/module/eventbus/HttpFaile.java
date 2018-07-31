package com.fltry.module.eventbus;

public class HttpFaile {

    private String falieMsg;
    //响应事件
    private int responseEvent;

    public HttpFaile(String falieMsg, int responseEvent) {
        this.falieMsg = falieMsg;
        this.responseEvent = responseEvent;
    }

    public String getFalieMsg() {
        return falieMsg;
    }

    public int getResponseEvent() {
        return responseEvent;
    }
}
