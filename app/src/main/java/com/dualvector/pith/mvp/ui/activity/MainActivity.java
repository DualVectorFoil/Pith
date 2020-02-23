package com.dualvector.pith.mvp.ui.activity;

import android.os.Bundle;

import com.dualvector.pith.R;
import com.dualvector.pith.di.component.DaggerMainComponent;
import com.dualvector.pith.di.module.MainModule;
import com.dualvector.pith.mvp.base.BaseActivity;
import com.dualvector.pith.mvp.contract.MainContract;
import com.dualvector.pith.mvp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
