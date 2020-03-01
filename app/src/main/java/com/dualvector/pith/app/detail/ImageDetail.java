package com.dualvector.pith.app.detail;

public class ImageDetail {

    private String mUrl;
    private float mWidth;
    private float mHeight;

    public ImageDetail(String url) {
        mUrl = url;
    }

    public ImageDetail(String url, float width, float height) {
        mUrl = url;
        mWidth = width;
        mHeight = height;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public float getWidth() {
        return mWidth;
    }

    public void setWidth(float mWidth) {
        this.mWidth = mWidth;
    }

    public float getHeight() {
        return mHeight;
    }

    public void setHeight(float mHeight) {
        this.mHeight = mHeight;
    }
}
