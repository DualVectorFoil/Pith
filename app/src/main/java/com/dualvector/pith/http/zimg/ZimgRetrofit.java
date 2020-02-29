package com.dualvector.pith.http.zimg;

import com.dualvector.pith.BuildConfig;
import com.dualvector.pith.app.constants.NetworkConstants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZimgRetrofit {

    private static final String TAG = "ZimgRetrofit";

    private static volatile ZimgRetrofit sInstance;

    private ZimgApiService mApiService;

    private ZimgRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ZimgApiService.MAX_REQUEST_TIME, TimeUnit.SECONDS)
                .readTimeout(ZimgApiService.MAX_REQUEST_TIME, TimeUnit.SECONDS)
                .writeTimeout(ZimgApiService.MAX_REQUEST_TIME, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new ZimgHeaderInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.ZIMG_SERVER_ADDR)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();

        mApiService = retrofit.create(ZimgApiService.class);
    }

    public static ZimgRetrofit getInstance() {
        if (sInstance == null) {
            synchronized (ZimgRetrofit.class) {
                if (sInstance == null)
                    sInstance = new ZimgRetrofit();
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
                        .observeOn(Schedulers.computation());
            }
        };
    }

    // TODO You should sync the codes when you subscribe
    public void uploadImage(RequestBody body, ZimgObserver scheduler) {
        mApiService.uploadImage(body)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void uploadImages(List<RequestBody> bodies, ZimgObserver scheduler) {
        mApiService.uploadImages(bodies)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
}
