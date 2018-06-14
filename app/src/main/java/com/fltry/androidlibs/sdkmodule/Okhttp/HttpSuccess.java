package com.fltry.androidlibs.sdkmodule.Okhttp;

import java.util.List;

/**
 * Created by tol on 2018-04-02.
 */

public class HttpSuccess<T> {
    /**
     * code : 1111
     * message : success
     * data : {"jwt":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkNTQzYjA0ZS00ZmY4LTRiNjctYjEzOS0xYzI0MDdmMzFiNjMiLCJpc3MiOiJEZWZhdWx0LmF1dGhlbi5HcmF5U3RvbmUuaXNzdWVyIiwiYXVkIjoicm9vdCIsInN1YiI6InJvb3QiLCJpYXQiOjE1MjI2NTIwMzMsIm5iZiI6MTUyMjY1MjAzMywiZXhwIjoxNTIyNzM4NDMzfQ.MpNwQERl0UJRUEwrtBZpX6OWnXFwJfbLOyo2IwrNtY0"}
     * excludes : null
     * traceInfo : []
     * sessionid : null
     * success : true
     * fail : false
     * timestamp : 1522658704290
     * status : 400
     * error : Bad Request
     * exception : com.graystone.rh.light.security.validator.ValidateException
     * path : /accounts/authen
     */
    private String code;
    private String message;
    private T data;
    private Object excludes;
    private Object sessionid;
    private boolean success;
    private boolean fail;
    private List<?> traceInfo;
    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private String path;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getExcludes() {
        return excludes;
    }

    public void setExcludes(Object excludes) {
        this.excludes = excludes;
    }

    public Object getSessionid() {
        return sessionid;
    }

    public void setSessionid(Object sessionid) {
        this.sessionid = sessionid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public List<?> getTraceInfo() {
        return traceInfo;
    }

    public void setTraceInfo(List<?> traceInfo) {
        this.traceInfo = traceInfo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpSuccess{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", excludes=" + excludes +
                ", sessionid=" + sessionid +
                ", success=" + success +
                ", fail=" + fail +
                ", traceInfo=" + traceInfo +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", exception='" + exception + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
