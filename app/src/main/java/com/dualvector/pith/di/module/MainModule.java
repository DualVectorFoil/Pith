package com.dualvector.pith.di.module;

import com.dualvector.pith.mvp.contract.MainContract;
import com.dualvector.pith.mvp.model.MainModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainContract.IMainView mView;

    public MainModule(MainContract.IMainView view) {
        mView = view;
    }

    @Provides
    MainContract.IMainView getView() {
        return mView;
    }

    @Provides
    MainContract.IMainModel getModel(MainModel model) {
        return model;
    }
}
