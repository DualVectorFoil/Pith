package com.dualvector.pith.mvp.model;

import com.dualvector.pith.mvp.contract.FrLoginContract;

import javax.inject.Inject;

import io.realm.Realm;

public class FrLoginModel implements FrLoginContract.IFrLoginModel {

    private static final String TAG = "FrLogin_Tag_Model";

    private Realm mRealm;

    @Inject
    public FrLoginModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {

    }
}
