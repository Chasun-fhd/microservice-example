package com.example.scccloudgateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc:
 * @Date: 2021/4/23
 * @Time: 10:22
 * @Author fenghd
 */
@Configuration
@ConfigurationProperties(prefix = "gateway.global")
@Getter
@Setter
public class GlobalUriConfiguration {

    /**
     * 需要鉴权uri
     */
    private String[] includeEurekaInstanceIds;

}
