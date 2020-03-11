package com.dualvector.pith.mvp.model;

import android.util.Log;

import com.dualvector.pith.http.BaseObserver;
import com.dualvector.pith.http.common.CommonRetrofit;
import com.dualvector.pith.http.zimg.ZimgHelper;
import com.dualvector.pith.http.zimg.ZimgObserver;
import com.dualvector.pith.http.zimg.ZimgRetrofit;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.contract.FrRegisterContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.mvp.model.bean.ZimgResponseBean;

import java.io.File;

import javax.inject.Inject;

import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FrRegisterModel implements FrRegisterContract.IFrRegisterModel {

    private static final String TAG = "FrRegisterModel";

    private static final int MAX_WAIT_TIME = 3000;

    private Realm mRealm;

    private String mAvatarUrl = "";
    private Object mLock = new Object();

    @Inject
    public FrRegisterModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void handleRegister(String phone, String userName, String password, File avatar, OnLoadDataListener<ProfileBean.DataBean> listener) {
        if (avatar != null && avatar.exists()) {
            RequestBody body = RequestBody.create(MediaType.parse("png"), avatar);
            ZimgRetrofit.getInstance().uploadImage(body, new ZimgObserver() {
                @Override
                protected void onSuccess(ZimgResponseBean.InfoBean bean) throws Exception {
                    synchronized (mLock) {
                        mAvatarUrl = ZimgHelper.normalUrl(bean.getMd5());
                        try {
                            mLock.notify();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                protected void onFailure(String error, boolean isNetworkError) throws Exception {
                    Log.e(TAG, "uploadImage failed, err: " + error);
                }
            });
        }

        synchronized (mLock) {
            try {
                mLock.wait(MAX_WAIT_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }
            CommonRetrofit.getInstance().register(phone, userName, password, mAvatarUrl, new BaseObserver<ProfileBean.DataBean>() {
                @Override
                protected void onSuccess(ProfileBean.DataBean bean) throws Exception {
                    listener.onSuccess(bean);
                }

                @Override
                protected void onFailure(String error, boolean isNetworkError) throws Exception {
                    Log.e(TAG, "register failed, err: " + error);
                }
            });
        }
    }

    @Override
    public void onDestroy() {

    }
}
