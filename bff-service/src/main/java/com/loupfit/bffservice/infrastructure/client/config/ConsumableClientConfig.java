package com.loupfit.bffservice.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumableClientConfig {

    @Bean(name = "consumableErrorDecoder")
    public ConsumableErrorDecoder consumableErrorDecoder() {
        return new ConsumableErrorDecoder();
    }
}
