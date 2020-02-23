package com.dualvector.pith.di.module;

import com.dualvector.pith.mvp.contract.LoginContract;
import com.dualvector.pith.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginContract.ILoginView mView;

    public LoginModule(LoginContract.ILoginView view) {
        mView = view;
    }

    @Provides
    LoginContract.ILoginView getView() {
        return mView;
    }

    @Provides
    LoginContract.ILoginModel getModel(LoginModel model) {
        return model;
    }
}
