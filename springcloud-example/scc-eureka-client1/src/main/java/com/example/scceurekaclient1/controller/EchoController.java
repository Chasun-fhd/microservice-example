package com.example.scceurekaclient1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Desc:
 * @Date: 2021/4/21
 * @Time: 16:30
 * @Author fenghd
 */
@RestController
public class EchoController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Client2Invoker client2Invoker;

    @RequestMapping(path = "/client1/echo")
    public String echo() {
        String result = client2Invoker.callEcho();
        String userId = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("userId");
        return "Get result from client2:" + result +", userId:" + userId;
    }

    @FeignClient("eureka-client2")
    interface Client2Invoker {

        @RequestMapping(path = "/client2/echo")
        String callEcho();
    }
}
