package com.dualvector.pith.mvp.model;

import android.util.Log;

import com.dualvector.pith.http.BaseObserver;
import com.dualvector.pith.http.common.CommonRetrofit;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.contract.FrLoginContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;

import javax.inject.Inject;

import io.realm.Realm;

public class FrLoginModel implements FrLoginContract.IFrLoginModel {

    private static final String TAG = "FrLogin_Tag_Model";

    private Realm mRealm;

    @Inject
    public FrLoginModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void handleLogin(String phone, String password, OnLoadDataListener<ProfileBean.DataBean> listener) {
        CommonRetrofit.getInstance().loginWithPassword(phone, password, new BaseObserver<ProfileBean.DataBean>() {
            @Override
            protected void onSuccess(ProfileBean.DataBean bean) throws Exception {
                listener.onSuccess(bean);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                Log.e(TAG, "login failed, err: " + error);
                listener.onFailure(error);
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
