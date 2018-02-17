package com.example.basic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.client.RestTemplate;

/*
 * Uses the Spring Boot Actuator endpoint to refresh the client.
 * Use this application to for instance refresh values from a configuration server. 
 */
@SpringBootApplication
public class RefreshApplication implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(RefreshApplication.class).web(false).run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("http://localhost:8080/refresh", null);	
	}
}
