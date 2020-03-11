package com.dualvector.pith.mvp.presenter;

import android.util.Log;

import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.contract.FrRegisterContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.util.AccountUtil;
import com.dualvector.pith.util.ToastUtil;

import java.io.File;

import javax.inject.Inject;

public class FrRegisterPresenter extends BasePresenter<FrRegisterContract.IFrRegisterModel, FrRegisterContract.IFrRegisterView> {

    private static final String TAG = "FrRegister_Tag_Presenter";

    @Inject
    public FrRegisterPresenter(FrRegisterContract.IFrRegisterModel model, FrRegisterContract.IFrRegisterView view) {
        super(model, view);
    }

    public void handleRegister(String phone, String userName, String password, String repeatPassword, File avatar) {
        if (phone == null || "".equals(phone)) {
            Log.e(TAG, "phone is illegal, userName: " + phone);
            ToastUtil.showToast("不符合规范的手机号");
            return;
        }
        if (userName == null || "".equals(userName)) {
            Log.e(TAG, "userName is illegal, userName: " + userName);
            ToastUtil.showToast("不符合规范的用户名");
            return;
        }
        if (password == null || "".equals(password)) {
            Log.e(TAG, "password is illegal, password: " + password);
            ToastUtil.showToast("不符合规范的密码");
            return;
        }
        if (!password.equals(repeatPassword)) {
            Log.e(TAG, "password is not equal with repeatPassword, password: " + password + ", repeatPassword: " + repeatPassword);
            ToastUtil.showToast("两次输入的密码不相同");
            return;
        }
        if (!AccountUtil.isIllegalRegisterInfo(phone, userName, password)) {
            return;
        }

        mModel.handleRegister(phone, userName, password, avatar, new OnLoadDataListener<ProfileBean.DataBean>() {
            @Override
            public void onSuccess(ProfileBean.DataBean dataBean) {
                AccountManager.getsInstance().setCookie(dataBean);
                mView.handleRegisterSuccess(dataBean);
            }

            @Override
            public void onFailure(String errMsg) {
                Log.d(TAG, "handleRegister failed, errMsg: " + errMsg);
                ToastUtil.showToast("注册失败，请检查网络");
            }
        });
    }
}
