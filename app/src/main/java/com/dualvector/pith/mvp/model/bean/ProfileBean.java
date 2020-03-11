package com.dualvector.pith.mvp.model.bean;

import com.dualvector.pith.mvp.base.BaseBean;

public class ProfileBean extends BaseBean<ProfileBean.DataBean> {

    public static class DataBean {

        private String userId;
        private String phoneNum;
        private String avatar_url;
        private String user_name;
        private String locale;
        private String bio;
        private int followers;
        private int following;
        private int artworkCount;
        private String pwd;
        private long registered_at;
        private long last_login_at;
        private String email;
        private String token;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getAvatarUrl() {
            return avatar_url;
        }

        public void setAvatarUrl(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getUserName() {
            return user_name;
        }

        public void setUserName(String user_name) {
            this.user_name = user_name;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

        public int getFollowing() {
            return following;
        }

        public void setFollowing(int following) {
            this.following = following;
        }

        public int getArtworkCount() {
            return artworkCount;
        }

        public void setArtworkCount(int artworkCount) {
            this.artworkCount = artworkCount;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
