package com.dualvector.pith.mvp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dualvector.pith.R;
import com.dualvector.pith.app.event.LoginEvent;
import com.dualvector.pith.app.event.RouteEvent;
import com.dualvector.pith.app.event.ShowRegisterTvEvent;
import com.dualvector.pith.app.manager.AccountManager;
import com.dualvector.pith.di.component.DaggerFrLoginComponent;
import com.dualvector.pith.di.module.FrLoginModule;
import com.dualvector.pith.mvp.base.BaseFragment;
import com.dualvector.pith.mvp.contract.FrLoginContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.mvp.presenter.FrLoginPresenter;
import com.dualvector.pith.mvp.ui.widget.PasswordEditText;
import com.dualvector.pith.mvp.ui.widget.PersonalCenterHeadView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment<FrLoginPresenter> implements FrLoginContract.IFrLoginView {

    private static final String TAG = "FrLogin_Tag_Fragment";

    @BindView(R.id.login_user_head)
    protected PersonalCenterHeadView mHeadView;
    @BindView(R.id.phone_et)
    protected EditText mPhoneEt;
    @BindView(R.id.password_et)
    protected PasswordEditText mPasswordEt;
    @BindView(R.id.forget_password_tv)
    protected TextView mForgetTv;
    @BindView(R.id.confirm_login_btn)
    protected Button mConfirmBtn;

    private String mPassword = "";
    private Activity mActivity;
    private ProfileBean.DataBean mCookie;

    RequestOptions mGlideHeadViewOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fallback( R.mipmap.ic_default_user_icon)
            .placeholder(R.mipmap.ic_default_user_icon)
            .error(R.mipmap.ic_default_user_icon);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerFrLoginComponent.builder().frLoginModule(new FrLoginModule(this)).build().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        EventBus.getDefault().post(new ShowRegisterTvEvent(ShowRegisterTvEvent.ON_SHOW));
        mActivity = getActivity();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mCookie = AccountManager.getsInstance().getCookie();
        if (mCookie != null) {
            mHeadView.setBackground(null);
            Glide.with(mActivity).load(mCookie.getAvatarUrl()).apply(mGlideHeadViewOptions).into(mHeadView);
            mPhoneEt.setText(mCookie.getPhoneNum());
            mPasswordEt.setText(mCookie.getPwd());
        }
    }

    @OnClick({R.id.phone_et, R.id.password_et, R.id.forget_password_tv, R.id.confirm_login_btn})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.phone_et:
                handleNameEtClick(view);
                break;
            case R.id.password_et:
                handlePasswordEtClick(view);
                break;
            case R.id.forget_password_tv:
                handleForgetTvClick(view);
                break;
            case R.id.login_user_head:
                handleHeadViewClick(view);
                break;
            case R.id.confirm_login_btn:
                handleConfirmLoginBtn(view);
                break;
            default:
        }
    }

    private void handleHeadViewClick(View view) {

    }

    private void handleNameEtClick(View view) {

    }

    private void handlePasswordEtClick(View view) {

    }

    private void handleForgetTvClick(View view) {
        EventBus.getDefault().post(new RouteEvent(RouteEvent.ON_OPEN_FORGET_FRAGMENT));
    }

    private void handleConfirmLoginBtn(View view) {
        mPresenter.handleLogin(mPhoneEt.getText().toString(), mPasswordEt.getText().toString());
    }

    @Override
    public void onStop() {
        super.onStop();
        mPasswordEt.setText("");
        if (mPasswordEt.isVisibililty()) {
            mPasswordEt.changePwdStatus();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!"".equals(mPassword)) {
            mPasswordEt.setText(mPassword);
            mPasswordEt.requestFocus();
            mPasswordEt.setSelection(mPassword.length());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPassword = mPasswordEt.getText().toString();
    }

    @Override
    public void handleLoginSuccess(ProfileBean.DataBean bean) {
        EventBus.getDefault().post(new LoginEvent(LoginEvent.ON_LOGIN_SUCCESS));
    }
}
