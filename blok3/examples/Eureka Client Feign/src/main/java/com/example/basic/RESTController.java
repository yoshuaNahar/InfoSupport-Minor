package com.example.basic;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@FeignClient("serviceinfo")
@RestController
public interface RESTController {

	// localhost:[port]
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String retrieveServiceInfo();
	
}
