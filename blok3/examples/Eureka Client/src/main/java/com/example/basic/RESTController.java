package com.example.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RESTController {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	// localhost:[port]/services/serviceinfo
	@RequestMapping("/services/{applicationName}")
	public List<ServiceInstance> services(@PathVariable String applicationName) {
		return discoveryClient.getInstances(applicationName);
	}
}
