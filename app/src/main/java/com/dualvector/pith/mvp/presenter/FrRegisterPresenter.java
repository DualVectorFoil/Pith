package com.dualvector.pith.mvp.presenter;

import android.util.Log;

import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.http.zimg.ZimgHelper;
import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.base.OnLoadDataListener;
import com.dualvector.pith.mvp.contract.FrRegisterContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.mvp.model.bean.ZimgResponseBean;
import com.dualvector.pith.util.StringUtil;
import com.dualvector.pith.util.ToastUtil;

import java.io.File;

import javax.inject.Inject;

public class FrRegisterPresenter extends BasePresenter<FrRegisterContract.IFrRegisterModel, FrRegisterContract.IFrRegisterView> {

    private static final String TAG = "FrRegister_Tag_Presenter";

    @Inject
    public FrRegisterPresenter(FrRegisterContract.IFrRegisterModel model, FrRegisterContract.IFrRegisterView view) {
        super(model, view);
    }

    public void handleRegister(String userName, String password, String repeatPassword, File avatar) {
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
        if (!isIllegalRegisterInfo(userName, password)) {
            return;
        }

        mModel.handleRegister(userName, password, avatar, new OnLoadDataListener<ProfileBean.DataBean>() {
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

    private boolean isIllegalRegisterInfo(String userName, String password) {
        if (StringUtil.isContainChinese(password)) {
            Log.w(TAG, "userName and password should not contains chineses");
            ToastUtil.showToast("密码不能包含中文");
            return false;
        }

        if (!StringUtil.isLetterDigitOrChinese(userName)) {
            Log.w(TAG, "userName should not contains special char");
            ToastUtil.showToast("用户名只能包含中文、字母与数字");
            return false;
        }

        if (userName.length() <= 0 || userName.length() > 8) {
            Log.w(TAG, "userName's length should less than 8");
            ToastUtil.showToast("用户名长度应不超过8位");
            return false;
        }

        if (password.length() < 6 || password.length() > 16) {
            Log.w(TAG, "password's length should less than 16");
            ToastUtil.showToast("密码长度应在6位与16位之间");
            return false;
        }

        return true;
    }
}
