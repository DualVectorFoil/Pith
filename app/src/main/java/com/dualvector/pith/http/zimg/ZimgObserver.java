package com.dualvector.pith.http.zimg;

import android.util.Log;

import com.dualvector.pith.app.constants.HttpStatus;
import com.dualvector.pith.http.ApiException;
import com.dualvector.pith.http.BaseObserver;
import com.dualvector.pith.mvp.model.bean.ZimgResponseBean;
import com.dualvector.pith.util.ToastUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class ZimgObserver implements Observer<ZimgResponseBean> {

    private static final String TAG = "ZimgObserver";

    @Override
    public void onNext(ZimgResponseBean response) {
        if (response.isRet()) {
            try {
                onSuccess(response.getInfo());
            } catch (Exception e) {
                Log.e(TAG, "" + e);
            }
        } else {
            try {
                onCodeError(response.getError().getCode());
                onError(new Throwable(response.getError().getMessage()));
            } catch (Exception e) {
                Log.e(TAG, "" + e);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }

        String error = null;
        if (e instanceof ConnectException) {
            error = HttpStatus.CONNECT_MSG;
        } else if (e instanceof HttpException) {
            error = ((HttpException) e).getLocalizedMessage() + "";
        } else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            switch (exception.getErrorCode()) {
                // TODO define server api error code
            }
        } else if (e instanceof JsonParseException || e instanceof JSONException) {
            error = HttpStatus.PARSE_MSG;
        } else if (e instanceof IOException) {
            if (e instanceof SocketTimeoutException) {
                error = HttpStatus.CONNECT_OUT_MSG;
            } else if ("Canceled".equals(e.getMessage()) || "Socket closed".equals(e.getMessage())) {
                return;
            } else {
                error = HttpStatus.CONNECT_MSG;
            }
        } else {
            error = e.getLocalizedMessage();
        }

        try {
            onFailure(error, false);
        } catch (Exception err) {
            Log.e(TAG, error + "\n" + err);
        }
    }

    @Override
    public void onComplete() {}

    @Override
    public void onSubscribe(Disposable d) {}

    protected abstract void onSuccess(ZimgResponseBean.InfoBean bean) throws Exception;

    protected abstract void onFailure(String error, boolean isNetworkError) throws Exception;

    protected void onCodeError(int errCode) {}
}
