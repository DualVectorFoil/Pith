package com.dualvector.pith.http.common;

import android.util.Log;

import com.dualvector.pith.BuildConfig;
import com.dualvector.pith.app.constants.NetworkConstants;
import com.dualvector.pith.http.BaseObserver;
import com.dualvector.pith.mvp.model.bean.ProfileBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonRetrofit {

    private static final String TAG = "CommonRetrofit";

    private static volatile CommonRetrofit sInstance;

    private CommonApiService mApiService;

    private CommonRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CommonApiService.MAX_REQUEST_TIME, TimeUnit.SECONDS)
                .readTimeout(CommonApiService.MAX_REQUEST_TIME, TimeUnit.SECONDS)
                .writeTimeout(CommonApiService.MAX_REQUEST_TIME, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        if (BuildConfig.DEBUG) {
            retrofitBuilder.baseUrl(NetworkConstants.DEBUG_PITH_SERVER_ADDR);
        } else {
            retrofitBuilder.baseUrl(NetworkConstants.PITH_SERVER_ADDR);
        }
        Retrofit retrofit = retrofitBuilder
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();

        mApiService = retrofit.create(CommonApiService.class);
    }

    public static CommonRetrofit getInstance() {
        if (sInstance == null) {
            synchronized (CommonRetrofit.class) {
                if (sInstance == null)
                    sInstance = new CommonRetrofit();
            }
        }
        return sInstance;
    }

    private ObservableTransformer threadTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void loginWithPassword(String userName, String password, BaseObserver<ProfileBean.DataBean> scheduler) {
        mApiService.loginWithPassword(userName, password)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void loginWithToken(String userName, String token, BaseObserver<ProfileBean.DataBean> scheduler) {
        mApiService.loginWithToken(userName, token)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void register(String userName, String password, String avatarUrl, BaseObserver<ProfileBean.DataBean> scheduler) {
        mApiService.register(userName, password, avatarUrl)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
}
