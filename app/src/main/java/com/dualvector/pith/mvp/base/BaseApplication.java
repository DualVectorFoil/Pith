package com.dualvector.pith.mvp.base;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.dualvector.pith.BuildConfig;
import com.dualvector.pith.app.constants.CommonConstants;
import com.dualvector.pith.mvp.ui.widget.AnimRefreshFooter;
import com.dualvector.pith.mvp.ui.widget.AnimRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import cn.jpush.android.api.JPushInterface;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initPush();
        initRealm();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        initSmartRefreshLayout();
    }

    public static synchronized Context getContext() {
        return mContext;
    }

    private void initPush() {
        if (BuildConfig.DEBUG) {
            JPushInterface.setDebugMode(true);
            JPushInterface.init(this);
        }
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(CommonConstants.DB_NAME)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void initSmartRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new AnimRefreshHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new AnimRefreshFooter(context);
            }
        });
    }
}
