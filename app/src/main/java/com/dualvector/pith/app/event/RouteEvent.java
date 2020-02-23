package com.dualvector.pith.app.event;

public class RouteEvent {

    public static final String ON_OPEN_FORGET_FRAGMENT = "0";
    public static final String ON_OPEN_LOGIN_FRAGMENT = "1";
    public static final String ON_OPEN_REGISTER_FRAGMENT = "2";

    private String mEventType;

    public RouteEvent(String eventType) {
        mEventType = eventType;
    }

    public boolean shouldProcess(String eventType) {
        return mEventType.equals(eventType);
    }
}
