package com.dualvector.pith.di.module;

import com.dualvector.pith.mvp.contract.FrLoginContract;
import com.dualvector.pith.mvp.model.FrLoginModel;

import dagger.Module;
import dagger.Provides;

@Module
public class FrLoginModule {

    private FrLoginContract.IFrLoginView mView;

    public FrLoginModule(FrLoginContract.IFrLoginView view) {
        mView = view;
    }

    @Provides
    FrLoginContract.IFrLoginView getView() {
        return mView;
    }

    @Provides
    FrLoginContract.IFrLoginModel getModel(FrLoginModel model) {
        return model;
    }
}
