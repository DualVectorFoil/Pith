package com.dualvector.pith.mvp.model;

import com.dualvector.pith.mvp.contract.LoginContract;

import javax.inject.Inject;

import io.realm.Realm;

public class LoginModel implements LoginContract.ILoginModel {

    private static final String TAG = "Login_Tag_Model";

    private Realm mRealm;

    @Inject
    public LoginModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {
        mRealm = null;
    }
}
