package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.controller.HystrixDemoService;

@SpringBootApplication
public class DemoPranayEurekaClient1Application {
	public static void main(String[] args) {
		SpringApplication.run(DemoPranayEurekaClient1Application.class, args);
	}
}

@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
class HystrixDemoApplication {
	@Autowired
	HystrixDemoService hystrixDemoService;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@RequestMapping("/")
	public String name() {
		String str = hystrixDemoService.getCustomerName();
		return "I'm A talking to "+str;
	}
	
}