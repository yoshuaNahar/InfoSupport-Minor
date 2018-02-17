package com.example.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
	@Autowired
	DiscoveryClient client;

	// localhost:[port]/
	@RequestMapping("/")
	public String retrieveServiceInfo() {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Service info. ID: " + localInstance.getServiceId()+ " host: " + localInstance.getHost() + " port: " + localInstance.getPort();
	}
}
