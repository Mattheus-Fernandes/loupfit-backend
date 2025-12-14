package com.loupfit.bffservice.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssetClientConfig {

    @Bean(name = "assetErrorDecoder")
    public AssetErrorDecoder assetErrorDecoder() {
        return new AssetErrorDecoder();
    }
}
