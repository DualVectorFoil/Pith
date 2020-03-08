package com.dualvector.pith.mvp.presenter;

import android.util.Log;

import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.contract.FrAccountContract;
import com.dualvector.pith.mvp.model.bean.ImageDetailBean;

import java.util.List;

import javax.inject.Inject;

public class FrAccountPresenter extends BasePresenter<FrAccountContract.IFrAccountModel, FrAccountContract.IFrAccountView> {

    private static final String TAG = "FrForget_Tag_Presenter";

    @Inject
    public FrAccountPresenter(FrAccountContract.IFrAccountModel model, FrAccountContract.IFrAccountView view) {
        super(model, view);
    }

    public void getSelfImages(int imagesRefreshType, int currentItemCount) {
        mModel.getSelfImages(currentItemCount, new OnLoadDataListener<List<ImageDetailBean.DataBean>>() {
            @Override
            public void onSuccess(List<ImageDetailBean.DataBean> dataBean) {
                mView.onGetSelfImagesSuccess(imagesRefreshType, dataBean);
            }

            @Override
            public void onFailure(String errMsg) {
                mView.onGetSelfImagesFailure(imagesRefreshType, errMsg);
                Log.w(TAG, "getSelfImages failed, refresh type: " + imagesRefreshType + ", err: " + errMsg);
            }
        });
    }

    public void getStarImages(int imagesRefreshType, int currentItemCount) {
        mModel.getStarImages(currentItemCount, new OnLoadDataListener<List<ImageDetailBean.DataBean>>() {
            @Override
            public void onSuccess(List<ImageDetailBean.DataBean> dataBean) {
                mView.onGetStartImagesSuccess(imagesRefreshType, dataBean);
            }

            @Override
            public void onFailure(String errMsg) {
                mView.onGetStarImagesFailure(imagesRefreshType, errMsg);
                Log.w(TAG, "getStarImages failed, refresh type: " + imagesRefreshType + ", err: " + errMsg);
            }
        });
    }

    public void getAtImages(int imagesRefreshType, int currentItemCount) {
        mModel.getAtImages(currentItemCount, new OnLoadDataListener<List<ImageDetailBean.DataBean>>() {
            @Override
            public void onSuccess(List<ImageDetailBean.DataBean> dataBean) {
                mView.onGetAtImagesSuccess(imagesRefreshType, dataBean);
            }

            @Override
            public void onFailure(String errMsg) {
                mView.onGetAtImagesFailure(imagesRefreshType, errMsg);
                Log.w(TAG, "getAtImages failed, refresh type: " + imagesRefreshType + ", err: " + errMsg);
            }
        });
    }
}
