package com.example.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CommunicationService {

	@Autowired
	private RestTemplate restTemplate;
	
	//@HystrixCommand(fallbackMethod = "fallbackText")
	public String fallback() {
		return restTemplate.getForEntity("http://terribleservice/", String.class).getBody();
	}
	
	public String fallbackText() {
		return "Fallback information";
	}
	
	@HystrixCommand(fallbackMethod = "fallbackText")
	public String fiftyPercent() {
		return restTemplate.getForEntity("http://terribleservice/50percentdelay", String.class).getBody();
	}
}
