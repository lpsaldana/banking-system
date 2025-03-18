package com.banking.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        GatewayFilter gatewayFilter = jwtFilter.apply(jwtFilter.newConfig());
        return builder.routes()
                .route("customer_service", r -> r.path("/customers/**")
                        .filters(f -> f.filter(gatewayFilter))
                        .uri("lb://customer-service"))
                .route("product_service", r -> r.path("/products/**")
                        .filters(f -> f.filter(gatewayFilter))
                        .uri("lb://product-service"))
                .route("transaction_service", r -> r.path("/transactions/**")
                        .filters(f -> f.filter(gatewayFilter))
                        .uri("lb://transaction-service"))
                .build();
    }
}
