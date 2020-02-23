package com.dualvector.pith.mvp.model.bean;

import com.dualvector.pith.mvp.base.BaseBean;

public class ProfileBean extends BaseBean<ProfileBean.DataBean> {

    public static class DataBean {
        /**
         * user_name : erjiguan
         * pwd : cool_master
         * avatar_url : http://XXX
         * registered_at : 324325234
         * last_login_at : 2353242
         */

        private String user_name;
        private String pwd;
        private String avatar_url;
        private long registered_at;
        private long last_login_at;

        public String getUserName() {
            return user_name;
        }

        public void setUserName(String user_name) {
            this.user_name = user_name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getAvatarUrl() {
            return avatar_url;
        }

        public void setAvatarUrl(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public long getRegisteredAt() {
            return registered_at;
        }

        public void setRegisteredAt(long registered_at) {
            this.registered_at = registered_at;
        }

        public long getLastLoginAt() {
            return last_login_at;
        }

        public void setLastLoginAt(long last_login_at) {
            this.last_login_at = last_login_at;
        }
    }
}
