package com.example.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaTerribleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaTerribleServiceApplication.class, args);
	}
}
