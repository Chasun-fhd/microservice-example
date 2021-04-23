package com.example.scccloudgateway.common;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

/**
 * @Desc:
 * @Date: 2021/4/23
 * @Time: 11:04
 * @Author fenghd
 */
@Getter
@Setter
public class GatewayResult<T> {

    private int code;
    private String msg;
    private T  data;

    public GatewayResult() {
    }

    public GatewayResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public GatewayResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public GatewayResult(GatewayResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public static <T> GatewayResult<T> fail(GatewayResultCode resultCode, String msg) {
        return new GatewayResult<T>(resultCode.getCode(), StringUtils.defaultIfBlank(msg, resultCode.getMsg()));
    }

    public static <T> GatewayResult<T> success() {
        return new GatewayResult<>(GatewayResultCode.SUCCESS);
    }
}
