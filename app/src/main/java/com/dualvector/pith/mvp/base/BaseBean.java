package com.dualvector.pith.mvp.base;

public abstract class BaseBean<T> {

    private int status_code;
    private String error_msg;
    private T data;

    public int getStatusCode() {
        return status_code;
    }

    public void setStatusCode(int status_code) {
        this.status_code = status_code;
    }

    public String getErrorMsg() {
        return error_msg;
    }

    public void setErrorMsg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
