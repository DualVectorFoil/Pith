package com.dualvector.pith.http.common;

import com.dualvector.pith.mvp.model.bean.ProfileBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CommonApiService {

    int MAX_REQUEST_TIME = 10;

    @FormUrlEncoded
    @POST("user/login")
    Observable<ProfileBean> loginWithPassword(@Field("user_name") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/login")
    Observable<ProfileBean> loginWithToken(@Field("user_name") String username, @Field("token") String token);

    @FormUrlEncoded
    @POST("user/register")
    Observable<ProfileBean> register(@Field("user_name") String username, @Field("password") String password, @Field("avatar_url") String avatar_url);
}
