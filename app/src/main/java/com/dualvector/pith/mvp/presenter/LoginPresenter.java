package com.dualvector.pith.mvp.presenter;

import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.contract.LoginContract;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.ILoginView> {

    private static final String TAG = "Login_Tag_Presenter";

    @Inject
    public LoginPresenter(LoginContract.ILoginModel model, LoginContract.ILoginView view) {
        super(model, view);
    }
}
