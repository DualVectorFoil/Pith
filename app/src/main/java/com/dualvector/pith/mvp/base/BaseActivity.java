package com.dualvector.pith.mvp.base;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.dualvector.pith.util.DialogUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IView, IActivity {

    @Inject
    protected P mPresenter;
    protected Dialog mDialog;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .init();
        mDialog = DialogUtils.createLoadingDialog(this, "");
        initView();
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
