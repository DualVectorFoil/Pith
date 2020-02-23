package com.dualvector.pith.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class RouteUtil {

    public static void startActivity(Context context, Class<? extends Activity> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> clazz, int result) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivityForResult(intent, result);
    }

    public static void startActivityForResult(Context context, Fragment fragment, Class<? extends Activity> clazz, int result) {
        Intent intent = new Intent(context, clazz);
        fragment.startActivityForResult(intent, result);
    }
}
