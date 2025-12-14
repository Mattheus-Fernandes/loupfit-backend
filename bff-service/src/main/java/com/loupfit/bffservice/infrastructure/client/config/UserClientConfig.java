package com.loupfit.bffservice.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserClientConfig {

    @Bean(name = "userErrorDecoder")
    public UserErrorDecoder userErrorDecoder() {
        return new UserErrorDecoder();
    }
}
