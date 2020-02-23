package com.dualvector.pith.di.module;

import com.dualvector.pith.mvp.contract.FrForgetContract;
import com.dualvector.pith.mvp.model.FrForgetModel;

import dagger.Module;
import dagger.Provides;

@Module
public class FrForgetModule {

    private FrForgetContract.IFrForgetView mView;

    public FrForgetModule(FrForgetContract.IFrForgetView view) {
        mView = view;
    }

    @Provides
    FrForgetContract.IFrForgetView getView() {
        return mView;
    }

    @Provides
    FrForgetContract.IFrForgetModel getModel(FrForgetModel model) {
        return model;
    }
}
