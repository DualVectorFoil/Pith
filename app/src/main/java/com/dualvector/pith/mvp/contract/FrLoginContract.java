package com.dualvector.pith.mvp.contract;

import com.dualvector.pith.mvp.base.IModel;
import com.dualvector.pith.mvp.base.IView;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.model.bean.ProfileBean;

public interface FrLoginContract {

    interface IFrLoginView extends IView {

        void handleLoginSuccess(ProfileBean.DataBean bean);
    }

    interface IFrLoginModel extends IModel {

        void handleLogin(String userName, String password, OnLoadDataListener<ProfileBean.DataBean> listener);
    }
}
