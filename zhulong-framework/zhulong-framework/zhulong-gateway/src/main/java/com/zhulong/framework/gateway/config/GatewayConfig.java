package com.zhulong.framework.gateway.config;

import com.zhulong.framework.auth.common.AuthConstance;
import com.zhulong.framework.gateway.filter.AuthFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by shanb on 2019-1-9.
 */
@Configuration
public class GatewayConfig {



    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

 /*   @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AddRequestHeaderGatewayFilterFactory headFactory) {
        return builder.routes()
                .route(r -> r.path("/test*//**")
                        .filters(f -> {
                           return f.stripPrefix(1);
                        })
                        .uri("lb://test")
                )
                .route(r -> r.path("/auth*//**")
                        .filters(f ->
                             f.stripPrefix(1)
                        )
                        .uri("lb://auth"))
                .build();
    }*/
}