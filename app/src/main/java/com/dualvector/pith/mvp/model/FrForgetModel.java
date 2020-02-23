package com.dualvector.pith.mvp.model;

import com.dualvector.pith.mvp.contract.FrForgetContract;

import javax.inject.Inject;

import io.realm.Realm;

public class FrForgetModel implements FrForgetContract.IFrForgetModel {

    private static final String TAG = "FrForget_Tag_Model";

    private Realm mRealm;

    @Inject
    public FrForgetModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {

    }
}
