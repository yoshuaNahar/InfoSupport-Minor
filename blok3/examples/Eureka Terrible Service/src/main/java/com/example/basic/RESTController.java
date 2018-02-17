package com.example.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
	@Autowired
	private DiscoveryClient client;

	private boolean sleep;
	
	
	
	public RESTController() {
		super();
		sleep = true;
	}

	// Every request has a 2 second delay
	// localhost:[port]/
	@RequestMapping("/")
	public String retrieveServiceInfoAfterTwoSeconds() throws InterruptedException {
		Thread.sleep(2000L);
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Service info. ID: " + localInstance.getServiceId()+ " host: " + localInstance.getHost() + " port: " + localInstance.getPort();
	}	
	
	// Half of the requests has a 2 second delay
	// localhost:[port]/50percentdelay
	@RequestMapping("/50percentdelay")
	public String retrieveServiceInfo50PercentDelay() throws InterruptedException {
		sleep = !sleep;
		if (sleep) {
			Thread.sleep(2000L);			
		}

		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Service info. ID: " + localInstance.getServiceId()+ " host: " + localInstance.getHost() + " port: " + localInstance.getPort();
	}
}
