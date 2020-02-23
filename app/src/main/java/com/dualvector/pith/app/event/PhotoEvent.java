package com.dualvector.pith.app.event;

import android.content.Intent;

public class PhotoEvent {

    public static final String ON_REQUEST_CAMERA = "0";
    public static final String ON_REQUEST_CROP = "1";
    public static final String ON_REQUEST_GALLERY = "2";

    private String mEventType;

    private Intent mData;

    public PhotoEvent(String eventType, Intent data) {
        mEventType = eventType;
        mData = data;
    }

    public boolean shouldProcess(String eventType) {
        return mEventType.equals(eventType);
    }

    public Intent getData() {
        return mData;
    }
}
