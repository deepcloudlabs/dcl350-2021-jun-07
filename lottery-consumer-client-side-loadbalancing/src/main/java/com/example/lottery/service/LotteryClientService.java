package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("unused")
public class LotteryClientService {
	private static final String LOTTERY_URL = "http://%s:%d/lottery/api/v1/numbers?column=10";
	private static final AtomicInteger counter = new AtomicInteger(0);
	@Autowired
	private DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	
	@PostConstruct
	public void init() {
		// spring.application.name
		this.instances = discoveryClient.getInstances("lottery");
	}
	
	//@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var restTemplate = new RestTemplate();
		// Load Balancing: RR (Round-Robin)
		// var index = counter.getAndIncrement() % instances.size();
		// Load Balancing: Random
		var index = ThreadLocalRandom.current().nextInt() % instances.size();
		var instance = instances.get(index);
		var host = instance.getHost();
		var port = instance.getPort();		
		var response = restTemplate.getForEntity(String.format(LOTTERY_URL, host,port), String.class).getBody();
		System.err.println(response);
	}
}
