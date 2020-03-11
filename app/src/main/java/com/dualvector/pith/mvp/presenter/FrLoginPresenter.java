package com.dualvector.pith.mvp.presenter;

import android.util.Log;

import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.contract.FrLoginContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.util.AccountUtil;
import com.dualvector.pith.util.ToastUtil;

import javax.inject.Inject;

public class FrLoginPresenter extends BasePresenter<FrLoginContract.IFrLoginModel, FrLoginContract.IFrLoginView> {

    private static final String TAG = "FrLogin_Tag_Presenter";

    @Inject
    public FrLoginPresenter(FrLoginContract.IFrLoginModel model, FrLoginContract.IFrLoginView view) {
        super(model, view);
    }

    public void handleLogin(String phone, String password) {
        if (phone == null || "".equals(phone)) {
            Log.e(TAG, "phone is illegal, phone: " + phone);
            ToastUtil.showToast("不符合规范的用户名");
            return;
        }
        if (password == null || "".equals(password)) {
            Log.e(TAG, "password is illegal, password: " + password);
            ToastUtil.showToast("不符合规范的密码");
            return;
        }
        if (!AccountUtil.isIllegalLoginInfo(phone, password)) {
            return;
        }

        mModel.handleLogin(phone, password, new OnLoadDataListener<ProfileBean.DataBean>() {
            @Override
            public void onSuccess(ProfileBean.DataBean dataBean) {
                AccountManager.getsInstance().setCookie(dataBean);
                mView.handleLoginSuccess(dataBean);
            }

            @Override
            public void onFailure(String errMsg) {
                Log.d(TAG, "handleLogin failed, errMsg: " + errMsg);
                ToastUtil.showToast("登陆失败，请检查账号信息或网络");
            }
        });
    }
}
