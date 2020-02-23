package com.dualvector.pith.di.module;

import com.dualvector.pith.mvp.contract.FrRegisterContract;
import com.dualvector.pith.mvp.model.FrRegisterModel;

import dagger.Module;
import dagger.Provides;

@Module
public class FrRegisterModule {

    private FrRegisterContract.IFrRegisterView mView;

    public FrRegisterModule(FrRegisterContract.IFrRegisterView view) {
        mView = view;
    }

    @Provides
    FrRegisterContract.IFrRegisterView getView() {
        return mView;
    }

    @Provides
    FrRegisterContract.IFrRegisterModel getModel(FrRegisterModel model) {
        return model;
    }
}
