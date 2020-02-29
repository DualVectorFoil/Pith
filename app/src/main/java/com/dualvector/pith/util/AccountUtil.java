package com.dualvector.pith.util;

import android.util.Log;

public class AccountUtil {

    private static final String TAG = "AccountUtil";

    public static boolean isIllegalRegisterInfo(String userName, String password) {
        if (StringUtil.isContainChinese(password)) {
            Log.w(TAG, "userName and password should not contains chineses");
            ToastUtil.showToast("密码不能包含中文");
            return false;
        }

        if (!StringUtil.isLetterDigitOrChinese(userName)) {
            Log.w(TAG, "userName should not contains special char");
            ToastUtil.showToast("用户名只能包含中文、字母与数字");
            return false;
        }

        if (userName.length() <= 0 || userName.length() > 8) {
            Log.w(TAG, "userName's length should less than 8");
            ToastUtil.showToast("用户名长度应不超过8位");
            return false;
        }

        if (password.length() < 6 || password.length() > 16) {
            Log.w(TAG, "password's length should less than 16");
            ToastUtil.showToast("密码长度应在6位与16位之间");
            return false;
        }

        return true;
    }
}
