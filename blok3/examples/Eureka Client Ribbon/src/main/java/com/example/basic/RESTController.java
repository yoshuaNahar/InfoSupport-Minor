package com.example.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RESTController {

	@Autowired
	private RestTemplate restTemplate;
	
	// localhost:[port]
	@RequestMapping("/")
	public String test() {
		return restTemplate.getForEntity("http://serviceinfo/", String.class).getBody();
	}
}
