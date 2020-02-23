package com.dualvector.pith.util;

import android.widget.Toast;

import com.dualvector.pith.mvp.base.BaseApplication;

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getContext(), content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }
}
