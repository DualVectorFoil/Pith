package com.dualvector.pith.mvp.presenter;

import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.contract.FrLoginContract;

import javax.inject.Inject;

public class FrLoginPresenter extends BasePresenter<FrLoginContract.IFrLoginModel, FrLoginContract.IFrLoginView> {

    private static final String TAG = "FrLogin_Tag_Presenter";

    @Inject
    public FrLoginPresenter(FrLoginContract.IFrLoginModel model, FrLoginContract.IFrLoginView view) {
        super(model, view);
    }
}
