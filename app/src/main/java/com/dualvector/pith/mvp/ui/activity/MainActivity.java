package com.dualvector.pith.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dualvector.pith.R;
import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.di.component.DaggerMainComponent;
import com.dualvector.pith.di.module.MainModule;
import com.dualvector.pith.mvp.base.BaseActivity;
import com.dualvector.pith.mvp.base.BaseApplication;
import com.dualvector.pith.mvp.contract.MainContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.mvp.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    private static final String TAG = "Main_Tag_Activity";

    @BindView(R.id.btn)
    protected Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO get cookie, and get user's home page data from network
        ProfileBean.DataBean bean = AccountManager.getsInstance().getCookie();
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

    @OnClick(R.id.btn)
    public void onClicked(View view) {
        Intent intent = new Intent(BaseApplication.getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
