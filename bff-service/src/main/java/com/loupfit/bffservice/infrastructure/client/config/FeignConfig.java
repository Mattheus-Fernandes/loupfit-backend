package com.loupfit.bffservice.infrastructure.client.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignError feignClient() {
        return new FeignError();
    }

    @Bean
    public Client feignErrorDecoder() {
        return new ApacheHttpClient();
    }
}
