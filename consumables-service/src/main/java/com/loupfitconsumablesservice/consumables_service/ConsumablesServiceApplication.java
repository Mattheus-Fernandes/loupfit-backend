package com.loupfitconsumablesservice.consumables_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumablesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumablesServiceApplication.class, args);
	}

}
