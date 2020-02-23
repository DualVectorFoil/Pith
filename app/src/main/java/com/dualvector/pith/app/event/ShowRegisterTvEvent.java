package com.dualvector.pith.app.event;

public class ShowRegisterTvEvent {

    public static final String ON_SHOW = "0";
    public static final String ON_HIDE = "1";

    private String mEventType;

    public ShowRegisterTvEvent(String eventType) {
        mEventType = eventType;
    }

    public boolean shouldProcess(String eventType) {
        return mEventType.equals(eventType);
    }
}
