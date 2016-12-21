package com.example.controller;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class HystrixDemoService {
	
	@HystrixCommand(fallbackMethod = "getFallbackCustomerName")
	public String getCustomerName() {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create("http://eureka-client-2");		// fails here
	    return restTemplate.getForObject(uri, String.class);
	}

	public String getFallbackCustomerName() {
		System.out.println("coming inside fallback method");
		return "Resillient Customer";
	}
}
