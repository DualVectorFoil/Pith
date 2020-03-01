package com.dualvector.pith.di.module;

import com.dualvector.pith.mvp.contract.FrAccountContract;
import com.dualvector.pith.mvp.model.FrAccountModel;

import dagger.Module;
import dagger.Provides;

@Module
public class FrAccountModule {

    private FrAccountContract.IFrAccountView mView;

    public FrAccountModule(FrAccountContract.IFrAccountView view) {
        mView = view;
    }

    @Provides
    FrAccountContract.IFrAccountView getView() {
        return mView;
    }

    @Provides
    FrAccountContract.IFrAccountModel getModel(FrAccountModel model) {
        return model;
    }
}
