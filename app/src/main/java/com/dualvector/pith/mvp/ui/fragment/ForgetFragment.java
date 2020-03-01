package com.dualvector.pith.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dualvector.pith.R;
import com.dualvector.pith.app.event.ShowRegisterTvEvent;
import com.dualvector.pith.mvp.base.BaseFragment;
import com.dualvector.pith.mvp.contract.FrForgetContract;
import com.dualvector.pith.mvp.presenter.FrForgetPresenter;

import org.greenrobot.eventbus.EventBus;

//import com.dualvector.pith.di.component.DaggerFrForgetComponent;

public class ForgetFragment extends BaseFragment<FrForgetPresenter> implements FrForgetContract.IFrForgetView {

    private static final String TAG = "FrForget_Tag_Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        DaggerFrForgetComponent.builder().frForgetModule(new FrForgetModule(this)).build().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_forget;
    }

    @Override
    public void initView() {
        EventBus.getDefault().post(new ShowRegisterTvEvent(ShowRegisterTvEvent.ON_HIDE));
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
