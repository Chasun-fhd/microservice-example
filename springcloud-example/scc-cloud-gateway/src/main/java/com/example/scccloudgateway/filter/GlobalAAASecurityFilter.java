package com.example.scccloudgateway.filter;

import com.example.scccloudgateway.common.GatewayResultCode;
import com.example.scccloudgateway.config.GlobalUriConfiguration;
import com.example.scccloudgateway.exception.GlobalAuthenticationException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Desc:
 * @Date: 2021/4/23
 * @Time: 10:20
 * @Author fenghd
 */
@Component
public class GlobalAAASecurityFilter implements GlobalFilter, Ordered {

    @Autowired
    private GlobalUriConfiguration globalUriConfig;

    private static final Logger LOG = LoggerFactory.getLogger(GlobalAAASecurityFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (ArrayUtils.isEmpty(globalUriConfig.getIncludeEurekaInstanceIds())) {
            return chain.filter(exchange);
        }
        Route currentRoute = (Route) exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        if (LOG.isInfoEnabled()) {
            LOG.info("Exclude instance {}, current route instance {}", globalUriConfig.getIncludeEurekaInstanceIds(),
                    currentRoute.getId());
        }
        if (!ArrayUtils.contains(globalUriConfig.getIncludeEurekaInstanceIds(), currentRoute.getId())) {
            return chain.filter(exchange);
        }
        List<String> attrs = exchange.getRequest().getHeaders().get("token");
        if (CollectionUtils.isEmpty(attrs) || StringUtils.isEmpty(attrs.get(0))) {
            throw new GlobalAuthenticationException(GatewayResultCode.AUTH_FAILED);
        }

        ServerHttpRequest request = exchange.getRequest().mutate().header("userId", "1111").build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
