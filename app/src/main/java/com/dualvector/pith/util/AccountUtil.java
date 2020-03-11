package com.dualvector.pith.util;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountUtil {

    private static final String TAG = "AccountUtil";

    public static boolean isIllegalRegisterInfo(String phone, String userName, String password) {
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

        if (!isIllegalPhoneNum(phone)) {
            Log.w(TAG, "is not illegal phone number");
            ToastUtil.showToast("手机号格式不正确");
            return false;
        }

        return true;
    }

    public static boolean isIllegalLoginInfo(String phone, String password) {
        if (StringUtil.isContainChinese(password)) {
            Log.w(TAG, "userName and password should not contains chineses");
            ToastUtil.showToast("密码不能包含中文");
            return false;
        }

        if (password.length() < 6 || password.length() > 16) {
            Log.w(TAG, "password's length should less than 16");
            ToastUtil.showToast("密码长度应在6位与16位之间");
            return false;
        }

        if (!isIllegalPhoneNum(phone)) {
            Log.w(TAG, "is not illegal phone number");
            ToastUtil.showToast("手机号格式不正确");
            return false;
        }

        return true;
    }

    private static boolean isIllegalPhoneNum(String phone) {
        if (phone != null && phone.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        }
        return false;
    }
}
