package com.niit.ApiGateWay.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p
                        .path("/user/v1/**")
                        .uri("http://authenticationService:8092/")
                ).route(p->p
                        .path("/contentManagement/v1/api/**")
                        .uri("http://contentmanagement-service:8083/")
                ).build();
    }
}
