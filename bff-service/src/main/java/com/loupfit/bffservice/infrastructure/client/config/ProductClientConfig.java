package com.loupfit.bffservice.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductClientConfig {

    @Bean(name = "productErrorEncoder")
    public ProductErrorDecoder productErrorDecoder() {
        return new ProductErrorDecoder();
    }
}