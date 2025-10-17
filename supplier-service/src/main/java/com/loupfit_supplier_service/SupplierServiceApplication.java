package com.loupfit_supplier_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SupplierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplierServiceApplication.class, args);
	}

}
