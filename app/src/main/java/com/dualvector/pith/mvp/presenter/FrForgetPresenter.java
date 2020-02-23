package com.dualvector.pith.mvp.presenter;

import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.contract.FrForgetContract;

import javax.inject.Inject;

public class FrForgetPresenter extends BasePresenter<FrForgetContract.IFrForgetModel, FrForgetContract.IFrForgetView> {

    private static final String TAG = "FrForget_Tag_Presenter";

    @Inject
    public FrForgetPresenter(FrForgetContract.IFrForgetModel model, FrForgetContract.IFrForgetView view) {
        super(model, view);
    }
}
