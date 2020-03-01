package com.dualvector.pith.mvp.presenter;

import com.dualvector.pith.app.detail.ImageDetail;
import com.dualvector.pith.mvp.base.BasePresenter;
import com.dualvector.pith.mvp.contract.FrAccountContract;

import java.util.List;

import javax.inject.Inject;

public class FrAccountPresenter extends BasePresenter<FrAccountContract.IFrAccountModel, FrAccountContract.IFrAccountView> {

    private static final String TAG = "FrForget_Tag_Presenter";

    @Inject
    public FrAccountPresenter(FrAccountContract.IFrAccountModel model, FrAccountContract.IFrAccountView view) {
        super(model, view);
    }

    public List<ImageDetail> getSelfImages() {
        return mModel.getSelfImages();
    }

    public List<ImageDetail> getStarImages() {
        return mModel.getStarImages();
    }

    public List<ImageDetail> getAtImages() {
        return mModel.getAtImages();
    }
}
