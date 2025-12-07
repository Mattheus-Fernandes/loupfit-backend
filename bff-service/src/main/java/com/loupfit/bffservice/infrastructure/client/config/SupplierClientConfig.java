package com.loupfit.bffservice.infrastructure.client.config;

import org.springframework.context.annotation.Bean;

public class SupplierClientConfig {
    @Bean(name = "supplierErrorDecoder")
    public SupplierErrorDecoder supplierErrorDecoder() {
        return new SupplierErrorDecoder();
    }
}
