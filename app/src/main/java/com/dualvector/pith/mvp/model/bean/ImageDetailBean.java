package com.dualvector.pith.mvp.model.bean;

import com.dualvector.pith.mvp.base.BaseBean;

import java.util.List;

public class ImageDetailBean extends BaseBean<List<ImageDetailBean.DataBean>> {

    public static final int USER_ACTIVITY_IMAGE = 0;
    public static final int STAR_ACTIVITY_IMAGE = 1;
    public static final int AT_ACTIVITY_IMAGE = 2;

    public static class DataBean {

        private String url;
        private float width;
        private float height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }
    }
}
