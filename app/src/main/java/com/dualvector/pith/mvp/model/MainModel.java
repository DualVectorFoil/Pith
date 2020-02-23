package com.dualvector.pith.mvp.model;

import com.dualvector.pith.mvp.contract.MainContract;

import javax.inject.Inject;

import io.realm.Realm;

public class MainModel implements MainContract.IMainModel {

    private static final String TAG = "Main_Tag_Model";

    private Realm mRealm;

    @Inject
    public MainModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {
        mRealm = null;
    }
}
