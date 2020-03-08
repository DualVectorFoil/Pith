package com.dualvector.pith.http;

import android.util.Log;

import com.dualvector.pith.BuildConfig;
import com.dualvector.pith.app.constants.HttpStatus;
import com.dualvector.pith.mvp.base.BaseBean;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<BaseBean<T>> {

    protected static final String TAG = "BaseObserver";

    protected BaseObserver() {}

    @Override
    public void onNext(BaseBean<T> response) {
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                onSuccess(response.getData());
            } catch (Exception e) {
                Log.e(TAG, "" + e);
            }
        } else {
            try {
                onCodeError(response.getStatusCode());
                onError(new Throwable(response.getErrorMsg()));
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

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "???   " + e);
        }
        try {
            onFailure(error, false);
        } catch (Exception err) {
            Log.e(TAG, error + "\n" + err);
        }
    }

    protected abstract void onSuccess(T bean) throws Exception;

    protected abstract void onFailure(String error, boolean isNetworkError) throws Exception;

    protected void onCodeError(int errCode) {}

    @Override
    public void onComplete() {}

    @Override
    public void onSubscribe(Disposable d) {}
}
