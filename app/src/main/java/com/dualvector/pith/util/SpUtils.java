package com.dualvector.pith.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dualvector.pith.BuildConfig;
import com.dualvector.pith.mvp.base.BaseApplication;
import com.dualvector.pith.mvp.model.bean.ProfileBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class SpUtils {

    private static final String TAG = "SpUtils";

    private static final String spFileName = "sputils";
    private static final SharedPreferences mSharedPreferences = BaseApplication.getContext().getSharedPreferences(spFileName, Activity.MODE_PRIVATE);
    private static final Gson mGson = new Gson();

    public static boolean setString(String key, String value) {
        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
            if (BuildConfig.DEBUG) {
                String strout = "Write SharedPreferences success key----" + key + "----value---" + value;
                Log.d(TAG, strout);
            }
        } catch (Exception e) {
            Log.e(TAG, "setString failed, err: " + e);
            return false;
        }
        return true;
    }

    public static String getString(String key) {
        String resultvalue = "";
        try {
            resultvalue = mSharedPreferences.getString(key, "");
            if (BuildConfig.DEBUG) {
                String strout = "get SharedPreferences success key----" + key + "----keyvalue---" + resultvalue;
                Log.d(TAG, strout);
            }
        } catch (Exception e) {
            Log.e(TAG, "getString failed, err: " + e);
            return "";
        }

        return resultvalue;
    }


    public static Boolean getBoolean(String key, Boolean strDefault) {
        return mSharedPreferences.getBoolean(key, strDefault);
    }


    public static void putBoolean(String strKey, Boolean strData) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(strKey, strData);
        editor.apply();
    }

    public static void setList(String key, List<String> kvs) {
        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            String str = mGson.toJson(kvs);
            editor.putString(key, str);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "SetList failed, err: " + e);
        }
    }

    public static List<String> getList(String key) {
        try {
            String resultvalue = mSharedPreferences.getString(key, "");
            return mGson.fromJson(resultvalue, new TypeToken<ArrayList<String>>(){}.getType());
        } catch (Exception e) {
            Log.e(TAG, "getList failed, err: " + e);
        }
        return null;
    }

    public static void setObject(String key, Object obj) {
        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            String str = mGson.toJson(obj);
            editor.putString(key, str);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "setObject failed, err: " + e);
        }
    }

    public static <T> T getObject(String key, Class<T> clazz) {
        try {
            String resultvalue = mSharedPreferences.getString(key, "");
            return mGson.fromJson(resultvalue, clazz);
        } catch (Exception e) {
            Log.e(TAG, "getObject failed, err: " + e);
        }
        return null;
    }
}
