package com.dualvector.pith.mvp.base;

public interface OnLoadDataListener<T> {

    void onSuccess(T t);

    void onFailure(String errMsg);
}
