package com.dualvector.pith.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dualvector.pith.BuildConfig;

import cn.jpush.android.api.JPushInterface;

public class PushMessageReceiver extends BroadcastReceiver {

    private static final String TAG = "PushMessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if (action == null || bundle == null) {
            Log.e(TAG, "received null push message or action");
            return;
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onReceive action: " + intent.getAction());
        }

        switch (action) {
            case JPushInterface.ACTION_REGISTRATION_ID:
                String registerId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onReceive Registration id: " + registerId);
                }
                // TODO save registerId on server
                break;
            case JPushInterface.ACTION_MESSAGE_RECEIVED:
                String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onReceive Registration id: " + message);
                }
                // TODO process customized message
                break;
            case JPushInterface.ACTION_NOTIFICATION_RECEIVED:
                int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onReceive Notification id: " + notificationId);
                }
                // TODO process notification message
                break;
            case JPushInterface.ACTION_NOTIFICATION_OPENED:
                // TODO make bundle into intent to open activity
                break;
            case JPushInterface.ACTION_RICHPUSH_CALLBACK:
                // TODO process rich push callback, ex: open an activity, or a website...
                break;
            case JPushInterface.ACTION_CONNECTION_CHANGE:
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Log.d(TAG, "connected state change to " + connected);
                // TODO process connect state
                break;
            default:
        }
    }
}
