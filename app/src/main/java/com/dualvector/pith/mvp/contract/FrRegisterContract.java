package com.dualvector.pith.mvp.contract;

import com.dualvector.pith.mvp.base.IModel;
import com.dualvector.pith.mvp.base.IView;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.model.bean.ProfileBean;

import java.io.File;

public interface FrRegisterContract {

    interface IFrRegisterView extends IView {

        void handleRegisterSuccess(ProfileBean.DataBean bean);
    }

    interface IFrRegisterModel extends IModel {

        void handleRegister(String phone, String userName, String password, File avatarurl, OnLoadDataListener<ProfileBean.DataBean> listener);
    }
}
