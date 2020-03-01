package com.dualvector.pith.mvp.contract;

import com.dualvector.pith.app.detail.ImageDetail;
import com.dualvector.pith.mvp.base.IModel;
import com.dualvector.pith.mvp.base.IView;

import java.util.List;

public interface FrAccountContract {

    interface IFrAccountView extends IView {

    }

    interface IFrAccountModel extends IModel {

        List<ImageDetail> getSelfImages();
        List<ImageDetail> getStarImages();
        List<ImageDetail> getAtImages();
    }
}
