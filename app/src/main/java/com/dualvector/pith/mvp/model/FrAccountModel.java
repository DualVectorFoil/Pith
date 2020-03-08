package com.dualvector.pith.mvp.model;

import android.util.Log;

import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.http.BaseObserver;
import com.dualvector.pith.http.common.CommonRetrofit;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.contract.FrAccountContract;
import com.dualvector.pith.mvp.model.bean.ImageDetailBean;
import com.dualvector.pith.mvp.model.bean.ProfileBean;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class FrAccountModel implements FrAccountContract.IFrAccountModel {

    private static final String TAG = "FrAccount_Tag_Model";

    private Realm mRealm;

    @Inject
    public FrAccountModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void getSelfImages(int currentItemCount, OnLoadDataListener<List<ImageDetailBean.DataBean>> listener) {
        ProfileBean.DataBean cookie = AccountManager.getsInstance().getCookie();
        if (cookie == null) {
            Log.e(TAG, "cookie should not be null when getSelfImages");
            listener.onFailure("cookie should not be null when getSelfImages");
            return;
        }

        CommonRetrofit.getInstance().getImages(currentItemCount, 0/*cookie.getUserId()*/, ImageDetailBean.USER_ACTIVITY_IMAGE, new BaseObserver<List<ImageDetailBean.DataBean>>() {
            @Override
            protected void onSuccess(List<ImageDetailBean.DataBean> bean) throws Exception {
                listener.onSuccess(bean);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                listener.onFailure(error);
            }
        });
    }

    @Override
    public void getStarImages(int currentItemCount, OnLoadDataListener<List<ImageDetailBean.DataBean>> listener) {
        ProfileBean.DataBean cookie = AccountManager.getsInstance().getCookie();
        if (cookie == null) {
            Log.e(TAG, "cookie should not be null when getSelfImages");
            listener.onFailure("cookie should not be null when getSelfImages");
            return;
        }

        CommonRetrofit.getInstance().getImages(currentItemCount, 0/*cookie.getUserId()*/, ImageDetailBean.STAR_ACTIVITY_IMAGE, new BaseObserver<List<ImageDetailBean.DataBean>>() {
            @Override
            protected void onSuccess(List<ImageDetailBean.DataBean> bean) throws Exception {
                listener.onSuccess(bean);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                listener.onFailure(error);
            }
        });
    }

    @Override
    public void getAtImages(int currentItemCount, OnLoadDataListener<List<ImageDetailBean.DataBean>> listener) {
        ProfileBean.DataBean cookie = AccountManager.getsInstance().getCookie();
        if (cookie == null) {
            Log.e(TAG, "cookie should not be null when getSelfImages");
            listener.onFailure("cookie should not be null when getSelfImages");
            return;
        }

        CommonRetrofit.getInstance().getImages(currentItemCount, 0/*cookie.getUserId()*/, ImageDetailBean.AT_ACTIVITY_IMAGE, new BaseObserver<List<ImageDetailBean.DataBean>>() {
            @Override
            protected void onSuccess(List<ImageDetailBean.DataBean> bean) throws Exception {
                listener.onSuccess(bean);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                listener.onFailure(error);
            }
        });
    }

    @Override
    public void onDestroy() {
    }
}
