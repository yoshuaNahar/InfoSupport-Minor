package com.example.basic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	// Available on http://localhost:8080/hello
	@RequestMapping("/hello")
	public String helloWorld() {
		return "Hello world";
	}
}
