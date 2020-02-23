package com.dualvector.pith.http.zimg;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ZimgHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Content-Type", "png");
        return chain.proceed(chain.request().newBuilder().headers(builder.build()).build());
    }
}
