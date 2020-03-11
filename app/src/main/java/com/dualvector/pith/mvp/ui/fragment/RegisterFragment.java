package com.dualvector.pith.mvp.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dualvector.pith.R;
import com.dualvector.pith.app.constants.PermissonConstants;
import com.dualvector.pith.app.event.LoginEvent;
import com.dualvector.pith.app.event.PhotoEvent;
import com.dualvector.pith.app.event.RouteEvent;
import com.dualvector.pith.app.event.ShowRegisterTvEvent;
import com.dualvector.pith.app.photo.PhotoHelper;
import com.dualvector.pith.di.component.DaggerFrRegisterComponent;
import com.dualvector.pith.di.module.FrRegisterModule;
import com.dualvector.pith.mvp.base.BaseFragment;
import com.dualvector.pith.mvp.contract.FrRegisterContract;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.mvp.presenter.FrRegisterPresenter;
import com.dualvector.pith.mvp.ui.widget.PasswordEditText;
import com.dualvector.pith.mvp.ui.widget.PersonalCenterHeadView;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

//import com.dualvector.pith.di.component.DaggerFrRegisterComponent;

public class RegisterFragment extends BaseFragment<FrRegisterPresenter> implements
        FrRegisterContract.IFrRegisterView, PasswordEditText.OnChangePwdStatusListener,
        DialogInterface.OnClickListener, EasyPermissions.PermissionCallbacks{

    private static final String TAG = "FrRegister_Tag_Fragment";

    private AppCompatActivity mActivity;

    @BindView(R.id.register_user_head)
    protected PersonalCenterHeadView mHeadView;
    @BindView(R.id.phone_et)
    protected EditText mPhoneEt;
    @BindView(R.id.name_et)
    protected EditText mNameEt;
    @BindView(R.id.password_et)
    protected PasswordEditText mPasswordEt;
    @BindView(R.id.repeat_password_et)
    protected PasswordEditText mRepeatPasswordEt;
    @BindView(R.id.login_direct_tv)
    protected TextView mLoginDirectTv;
    @BindView(R.id.confirm_register_btn)
    protected Button mConfirmRegisterBtn;

    private Dialog mItemDialog;
    private File mHeadImageFile;
    private File mCropImageFile;
    private String mPassword = "";
    private String mRepeatPassword = "";

    RequestOptions mGlideHeadViewOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .fallback( R.mipmap.ic_default_user_icon)
            .placeholder(R.mipmap.ic_default_user_icon)
            .error(R.mipmap.ic_default_user_icon);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerFrRegisterComponent.builder().frRegisterModule(new FrRegisterModule(this)).build().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mActivity = (AppCompatActivity) getActivity();
        EventBus.getDefault().post(new ShowRegisterTvEvent(ShowRegisterTvEvent.ON_HIDE));

        mPasswordEt.setOnChangePwdStatusListener(this);
        mRepeatPasswordEt.setOnChangePwdStatusListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.register_user_head, R.id.name_et, R.id.phone_et, R.id.password_et,
            R.id.repeat_password_et, R.id.login_direct_tv, R.id.confirm_register_btn})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.register_user_head:
                handleHeadViewClick(view);
                break;
            case R.id.name_et:
                handleNameEtClick(view);
                break;
            case R.id.phone_et:
                handlePhoneEtClick(view);
                break;
            case R.id.password_et:
                handlePasswordEtClick(view);
                break;
            case R.id.repeat_password_et:
                handleRepeatPasswordEtClick(view);
                break;
            case R.id.login_direct_tv:
                handleLoginDirectTvClick(view);
                break;
            case R.id.confirm_register_btn:
                handleConfirmLoginBtnClick(view);
                break;
            default:
        }
    }

    private void handleHeadViewClick(View view) {
//        if (mItemDialog == null) {
//            mItemDialog = DialogUtil.createItemDialog(mActivity, new String[]{"拍照", "相册"}, this);
//        }
//        mItemDialog.show();
        if (EasyPermissions.hasPermissions(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            PhotoHelper.getInstance().gallery(mActivity);
        } else {
            EasyPermissions.requestPermissions(mActivity, "需要相册权限", PermissonConstants.PERMISSION_READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void handlePhoneEtClick(View view) {

    }

    private void handleNameEtClick(View view) {

    }

    private void handlePasswordEtClick(View view) {

    }

    private void handleRepeatPasswordEtClick(View view) {
    }

    private void handleLoginDirectTvClick(View view) {
        EventBus.getDefault().post(new RouteEvent(RouteEvent.ON_OPEN_LOGIN_FRAGMENT));
    }

    private void handleConfirmLoginBtnClick(View view) {
        mPresenter.handleRegister(mPhoneEt.getText().toString(), mNameEt.getText().toString(), mPasswordEt.getText().toString(),
                mRepeatPasswordEt.getText().toString(), mCropImageFile);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhotoCallback(PhotoEvent event) {
        if (event.shouldProcess(PhotoEvent.ON_REQUEST_CAMERA)) {
            tryCrop(mHeadImageFile.getAbsolutePath());
        } else if (event.shouldProcess(PhotoEvent.ON_REQUEST_CROP)) {
            Uri cropImageUri = UCrop.getOutput(event.getData());
            mHeadView.setBackground(null);
            Glide.with(mActivity).load(cropImageUri).apply(mGlideHeadViewOptions).into(mHeadView);
        } else {
            tryCrop(PhotoHelper.getInstance().handleImage(mActivity, event.getData()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!"".equals(mRepeatPassword)) {
            mPasswordEt.setText(mPassword);
            mRepeatPasswordEt.requestFocus();
            mRepeatPasswordEt.setSelection(mRepeatPassword.length());
        } else if (!"".equals(mPassword)) {
            mRepeatPasswordEt.setText(mRepeatPassword);
            mPasswordEt.requestFocus();
            mPasswordEt.setSelection(mPassword.length());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPassword = mPasswordEt.getText().toString();
        mRepeatPassword = mRepeatPasswordEt.getText().toString();
    }

    @Override
    public void onChange(PasswordEditText et) {
        if (et.equals(mPasswordEt)) {
            mRepeatPasswordEt.changePwdStatus();
        } else {
            mPasswordEt.changePwdStatus();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mPasswordEt.setText("");
        mRepeatPasswordEt.setText("");
        if (mPasswordEt.isVisibililty()) {
            mPasswordEt.changePwdStatus();
        }
        if (mRepeatPasswordEt.isVisibililty()) {
            mRepeatPasswordEt.changePwdStatus();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int index) {
        if (index == 0) {
            if (EasyPermissions.hasPermissions(mActivity, Manifest.permission.CAMERA)) {
                mHeadImageFile = PhotoHelper.getInstance().camera(mActivity);
            } else {
                EasyPermissions.requestPermissions(mActivity, "需要相机权限", PermissonConstants.PERMISSION_CAMERA, Manifest.permission.CAMERA);
            }
        } else {
            if (EasyPermissions.hasPermissions(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                PhotoHelper.getInstance().gallery(mActivity);
            } else {
                EasyPermissions.requestPermissions(mActivity, "需要相册权限", PermissonConstants.PERMISSION_READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case PermissonConstants.PERMISSION_CAMERA:
                mHeadImageFile = PhotoHelper.getInstance().camera(mActivity);
                break;
            case PermissonConstants.PERMISSION_READ_EXTERNAL_STORAGE:
                PhotoHelper.getInstance().gallery(mActivity);
                break;
            default:
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case PermissonConstants.PERMISSION_CAMERA:
                Toast.makeText(mActivity, "请授予相机权限", Toast.LENGTH_SHORT).show();
                break;
            case PermissonConstants.PERMISSION_READ_EXTERNAL_STORAGE:
                Toast.makeText(mActivity, "请授予相册权限", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

    @Override
    public void handleRegisterSuccess(ProfileBean.DataBean bean) {
        EventBus.getDefault().post(new LoginEvent(LoginEvent.ON_LOGIN_SUCCESS));
    }

    private void tryCrop(String path) {
        mCropImageFile = PhotoHelper.getInstance().crop(mActivity, this, path);
    }
}
