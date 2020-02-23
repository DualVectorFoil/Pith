package com.dualvector.pith.mvp.base;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.dualvector.pith.util.DialogUtil;
import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IView, IActivity {

    @Inject
    protected P mPresenter;
    protected Dialog mDialog;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .init();
        mDialog = DialogUtil.createLoadingDialog(this, "");
        initView();
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
