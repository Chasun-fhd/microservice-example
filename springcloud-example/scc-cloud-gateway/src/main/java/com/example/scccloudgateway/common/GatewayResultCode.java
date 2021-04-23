package com.example.scccloudgateway.common;

/**
 * @Desc:
 * @Date: 2021/4/23
 * @Time: 10:59
 * @Author fenghd
 */
public enum GatewayResultCode {

    AUTH_FAILED(403, "token鉴权失败"),
    GATEWAY_ERROR(500, "服务调用失败"),
    SUCCESS(200, "处理成功");

    private int code;
    private String msg;

    GatewayResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
