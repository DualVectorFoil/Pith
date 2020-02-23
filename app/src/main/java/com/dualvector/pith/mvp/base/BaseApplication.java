package com.dualvector.pith.mvp.base;

import android.app.Application;
import android.content.Context;

import com.dualvector.pith.app.constants.Constants;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    private static Context mContext;

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mRefWatcher = setupLeakCanary();
        initRealm();
    }

    public static synchronized Context getContext() {
        return mContext;
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication leakApplication = (BaseApplication) context.getApplicationContext();
        return leakApplication.mRefWatcher;
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Constants.DB_NAME)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
