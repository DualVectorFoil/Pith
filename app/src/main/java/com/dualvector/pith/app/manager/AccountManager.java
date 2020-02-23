package com.dualvector.pith.app.manager;

import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.dualvector.pith.util.SpUtils;

public class AccountManager {

    private static final String TAG = "AccountManager";

    private static final String COOKIE_KEY = "account_cookie";

    private static volatile AccountManager sInstance;

    private int mHasCookie = -1;
    private ProfileBean.DataBean mCookie;

    public static AccountManager getsInstance() {
        if (sInstance == null) {
            synchronized (AccountManager.class) {
                if (sInstance == null) {
                    sInstance = new AccountManager();
                }
            }
        }
        return sInstance;
    }

    public synchronized void setCookie(ProfileBean.DataBean bean) {
        mCookie = bean;
        mHasCookie = 1;
        SpUtils.setObject(COOKIE_KEY, bean);
    }

    public synchronized ProfileBean.DataBean getCookie() {
        if (hasCookie()) {
            return mCookie;
        }
        return null;
    }

    public boolean hasCookie() {
        if (mHasCookie != -1) {
            return mHasCookie == 1;
        }

        mCookie = SpUtils.getObject(COOKIE_KEY, ProfileBean.DataBean.class);
        if (mCookie == null) {
            mHasCookie = 0;
            return false;
        } else {
            mHasCookie = 1;
            return true;
        }
    }
}
