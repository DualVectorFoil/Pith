package com.dualvector.pith.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dualvector.pith.BuildConfig;
import com.dualvector.pith.R;
import com.dualvector.pith.app.event.LoginEvent;
import com.dualvector.pith.app.event.PhotoEvent;
import com.dualvector.pith.app.event.RouteEvent;
import com.dualvector.pith.app.event.ShowRegisterTvEvent;
import com.dualvector.pith.app.photo.PhotoHelper;
import com.dualvector.pith.di.component.DaggerLoginComponent;
import com.dualvector.pith.di.module.LoginModule;
import com.dualvector.pith.mvp.base.BaseActivity;
import com.dualvector.pith.mvp.contract.LoginContract;
import com.dualvector.pith.mvp.presenter.LoginPresenter;
import com.dualvector.pith.mvp.ui.fragment.ForgetFragment;
import com.dualvector.pith.mvp.ui.fragment.LoginFragment;
import com.dualvector.pith.mvp.ui.fragment.RegisterFragment;
import com.dualvector.pith.util.RouteUtil;
import com.gyf.immersionbar.ImmersionBar;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    private static final String TAG = "Login_Tag_Activity";

    private FragmentManager mFragmentManager;
    private Fragment mLoginFragment;
    private Fragment mRegisterFragment;
    private Fragment mForgetFragment;
    private Fragment mCurFragment;

    @BindView(R.id.back_btn_tool_bar)
    protected ImageView mBackBtn;
    @BindView(R.id.register_tv_tool_bar)
    protected TextView mRegisterBtn;

    public static LoginCallback mLoginCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mFragmentManager = getSupportFragmentManager();
        mLoginFragment = new LoginFragment();
        switchFragment(mLoginFragment);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "requestCode: " + requestCode + ", resultCode: " + resultCode);
        }

        if (resultCode == UCrop.RESULT_ERROR) {
            Log.e(TAG, "crop failed, err: " + UCrop.getError(data));
            return;
        }

        switch (requestCode) {
            case PhotoHelper.REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {
                    EventBus.getDefault().post(new PhotoEvent(PhotoEvent.ON_REQUEST_CAMERA, null));
                }
                break;
            case PhotoHelper.REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    EventBus.getDefault().post(new PhotoEvent(PhotoEvent.ON_REQUEST_GALLERY, data));
                }
                break;
            case UCrop.REQUEST_CROP:
                if (resultCode == RESULT_OK && data != null) {
                    EventBus.getDefault().post(new PhotoEvent(PhotoEvent.ON_REQUEST_CROP, data));
                }
                break;
            default:
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.back_btn_tool_bar, R.id.register_tv_tool_bar})
    public void onClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.back_btn_tool_bar:
                handleBackBtnClick(view);
                break;
            case R.id.register_tv_tool_bar:
                handleRegisterTvClick(view);
                break;
            default:
        }
    }

    private void handleBackBtnClick(View view) {
        onBackPressed();
    }

    private void handleRegisterTvClick(View view) {
        if (mRegisterFragment == null) {
            mRegisterFragment = new RegisterFragment();
        }
        switchFragment(mRegisterFragment);
    }

    private void switchFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().replace(R.id.login_fragment_container, fragment)
                .addToBackStack(fragment.getClass().getName()).commitAllowingStateLoss();
        mCurFragment = fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchFragment(RouteEvent event) {
        if (event.shouldProcess(RouteEvent.ON_OPEN_FORGET_FRAGMENT)) {
            if (mForgetFragment == null) {
                mForgetFragment = new ForgetFragment();
            }
            switchFragment(mForgetFragment);
        } else if (event.shouldProcess(RouteEvent.ON_OPEN_LOGIN_FRAGMENT)) {
            if (mLoginFragment == null) {
                mLoginFragment = new LoginFragment();
            }
            switchFragment(mLoginFragment);
        } else {
            if (mRegisterFragment == null) {
                mRegisterFragment = new RegisterFragment();
            }
            switchFragment(mRegisterFragment);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowRegisterBtn(ShowRegisterTvEvent event) {
        if (event.shouldProcess(ShowRegisterTvEvent.ON_SHOW)) {
            mRegisterBtn.setVisibility(View.VISIBLE);
        } else {
            mRegisterBtn.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccess(LoginEvent event) {
        if (!event.shouldProcess(LoginEvent.ON_LOGIN_SUCCESS)) {
            return;
        }
        RouteUtil.startActivity(this, MainActivity.class);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mCurFragment.equals(mLoginFragment) || mFragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            mFragmentManager.popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public interface LoginCallback {
        void onLogin();
    }
}
