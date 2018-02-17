package com.example.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RESTController {

	@Autowired
	private CommunicationService communicationService;
	
	// localhost:[port]
	@RequestMapping("/")
	public String fallback() {
		return communicationService.fallback();
	}
	
	// localhost:[port]/50percent
	@RequestMapping("/50percent")
	public String fiftyPercent() {
		return communicationService.fiftyPercent();
	}
	
	
	
}
