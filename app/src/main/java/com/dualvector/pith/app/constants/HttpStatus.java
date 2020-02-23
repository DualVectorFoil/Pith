package com.dualvector.pith.app.constants;

public class HttpStatus {

    public static final int OK = 200;

    public static final int UNAU = 402;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;

    public static final String NETWORK_MSG = "网络错误";
    public static final String COOKIE_OUT_MSG = "登录过期，请重新登录";
    public static final String PARSE_MSG = "服务器数据解析错误";
    public static final String UNKNOWN_MSG = "未知错误";
    public static final String CONNECT_MSG = "连接服务器错误,请检查网络";
    public static final String CONNECT_OUT_MSG = "连接服务器超时,请检查网络";
}
