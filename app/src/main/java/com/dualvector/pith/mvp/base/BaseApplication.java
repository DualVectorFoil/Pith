package com.dualvector.pith.mvp.base;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.dualvector.pith.app.constants.CommonConstants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initRealm();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    public static synchronized Context getContext() {
        return mContext;
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(CommonConstants.DB_NAME)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
