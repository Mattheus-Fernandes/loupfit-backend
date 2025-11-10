package com.loupfit.bffservice.infrastructure.client.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class FeignConfig {

    @Bean
    public Client feignClient() {
        return new ApacheHttpClient();
    }
}
