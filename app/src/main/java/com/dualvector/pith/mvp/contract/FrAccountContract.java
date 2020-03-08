package com.dualvector.pith.mvp.contract;

import com.dualvector.pith.mvp.base.IModel;
import com.dualvector.pith.mvp.base.IView;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.model.bean.ImageDetailBean;

import java.util.List;

public interface FrAccountContract {

    int FLAG_REFRESH = 0;
    int FLAG_LOAD_MORE = 1;

    interface IFrAccountView extends IView {

        void onGetSelfImagesSuccess(int imagesRefreshType, List<ImageDetailBean.DataBean> bean);
        void onGetStartImagesSuccess(int imagesRefreshType, List<ImageDetailBean.DataBean> bean);
        void onGetAtImagesSuccess(int imagesRefreshType, List<ImageDetailBean.DataBean> bean);
        void onGetSelfImagesFailure(int imagesRefreshType, String errMsg);
        void onGetStarImagesFailure(int imagesRefreshType, String errMsg);
        void onGetAtImagesFailure(int imagesRefreshType, String errMsg);
    }

    interface IFrAccountModel extends IModel {

        void getSelfImages(int currentItemCount, OnLoadDataListener<List<ImageDetailBean.DataBean>> listener);
        void getStarImages(int currentItemCount, OnLoadDataListener<List<ImageDetailBean.DataBean>> listener);
        void getAtImages(int currentItemCount, OnLoadDataListener<List<ImageDetailBean.DataBean>> listener);
    }
}
