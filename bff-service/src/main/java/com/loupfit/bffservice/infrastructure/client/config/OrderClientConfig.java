package com.loupfit.bffservice.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderClientConfig {

    @Bean(name = "orderErrorDecoder")
    public OrderErrorDecoder orderErrorDecoder() {
        return new OrderErrorDecoder();
    }
}
