package com.dualvector.pith.app.event;

public class LoginEvent {

    public static final String ON_LOGIN_SUCCESS = "0";

    private String mEventType;

    public LoginEvent(String eventType) {
        mEventType = eventType;
    }

    public boolean shouldProcess(String eventType) {
        return mEventType.equals(eventType);
    }
}
