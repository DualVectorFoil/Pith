package com.dualvector.pith.http.common;

import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.mvp.model.bean.ProfileBean;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;

public class CommonHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        ProfileBean.DataBean cookie = AccountManager.getsInstance().getCookie();
        if (cookie != null) {
            builder.add("Authorization", cookie.getToken());
        }
        return chain.proceed(chain.request().newBuilder().headers(builder.build()).build());
    }
}
