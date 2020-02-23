package com.dualvector.pith.http.zimg;

import com.dualvector.pith.app.constants.NetworkConstants;

public class ZimgHelper {

    private static final String BASE_URL = NetworkConstants.ZIMG_SERVER_ADDR + "/";

    public static String normalUrl(String md5) {
        StringBuilder builder = new StringBuilder(BASE_URL);
        builder.append(md5);
        return builder.toString();
    }

    public static String originUrl(String md5) {
        StringBuilder builder = new StringBuilder(BASE_URL);
        builder.append(md5);
        builder.append("?p=0");
        return builder.toString();
    }
}
