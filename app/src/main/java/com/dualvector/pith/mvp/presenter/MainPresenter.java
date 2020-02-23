package com.dualvector.pith.mvp.presenter;

import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.contract.MainContract;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.IMainModel, MainContract.IMainView> {

    private static final String TAG = "Main_Tag_Presenter";

    @Inject
    public MainPresenter(MainContract.IMainModel model, MainContract.IMainView view) {
        super(model, view);
    }
}
