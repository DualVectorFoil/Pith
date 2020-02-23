/*
    Because this bean is responsed from another server, its' reponsed json format is different from
    my server, so it should not extend BaseBean
 */
package com.dualvector.pith.mvp.model.bean;

import com.dualvector.pith.mvp.base.BaseBean;

public class ZimgResponseBean extends BaseBean<ZimgResponseBean.InfoBean> {
    /**
     * ret : true
     * info : {"md5":"5f189d8ec57f5a5a0d3dcba47fa797e2","size":29615}
     * error : {"code":7,"message":"File too large."}
     */

    private boolean ret;
    private InfoBean info;
    private ErrorBean error;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class InfoBean {
        /**
         * md5 : 5f189d8ec57f5a5a0d3dcba47fa797e2
         * size : 29615
         */

        private String md5;
        private int size;

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static class ErrorBean {
        /**
         * code : 7
         * message : File too large.
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
