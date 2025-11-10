package com.loupfit.bffservice.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserClientConfig {

    @Bean
    public UserErrorDecoder feignError() {
        return new UserErrorDecoder();
    }
}
