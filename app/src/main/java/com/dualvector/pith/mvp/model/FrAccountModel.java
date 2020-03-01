package com.dualvector.pith.mvp.model;

import com.dualvector.pith.app.detail.ImageDetail;
import com.dualvector.pith.mvp.contract.FrAccountContract;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class FrAccountModel implements FrAccountContract.IFrAccountModel {

    private static final String TAG = "FrAccount_Tag_Model";

    private Realm mRealm;

    @Inject
    public FrAccountModel() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public List<ImageDetail> getSelfImages() {
        return null;
    }

    @Override
    public List<ImageDetail> getStarImages() {
        return null;
    }

    @Override
    public List<ImageDetail> getAtImages() {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
