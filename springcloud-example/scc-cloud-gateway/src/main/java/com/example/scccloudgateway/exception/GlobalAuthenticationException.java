package com.example.scccloudgateway.exception;

import com.example.scccloudgateway.common.GatewayResultCode;

/**
 * @Desc:
 * @Date: 2021/4/23
 * @Time: 10:56
 * @Author fenghd
 */
public class GlobalAuthenticationException extends RuntimeException{

    public GlobalAuthenticationException(GatewayResultCode resultCode) {
        this(resultCode.getMsg());
    }

    public GlobalAuthenticationException(String message) {
        super(message);
    }

    public GlobalAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
