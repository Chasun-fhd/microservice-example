package com.example.scceurekaclient2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc:
 * @Date: 2021/4/21
 * @Time: 16:24
 * @Author fenghd
 */
@RestController
public class EchoController {

    @RequestMapping(path = "/client2/echo")
    public String echo() {
        return "Hello,I'm client2";
    }
}
