package com.dualvector.pith.mvp.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dualvector.pith.util.DialogUtils;
import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements IView, IFragment {

    @Inject
    protected P mPresenter;
    protected View mRootView;
    protected Dialog mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayout(), null);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        mDialog = DialogUtils.createLoadingDialog(getActivity(), "请稍后...");

        initView();
        initData(savedInstanceState);
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Context context = getActivity();
        if (context == null) {
            Log.e("RefWatcherErr", "Activity of fragment is null, init RefWatcher failed.");
            return;
        }
        RefWatcher refWatcher = BaseApplication.getRefWatcher(context);
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
