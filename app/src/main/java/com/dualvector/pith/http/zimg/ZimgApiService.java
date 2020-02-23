package com.dualvector.pith.http.zimg;

import com.dualvector.pith.mvp.model.bean.ZimgResponseBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ZimgApiService {

    int MAX_REQUEST_TIME = 10;

    @POST("upload")
    Observable<ZimgResponseBean> uploadImage(@Body RequestBody body);

    @POST("upload")
    Observable<ZimgResponseBean> uploadImages(@Body List<RequestBody> bodies);
}
