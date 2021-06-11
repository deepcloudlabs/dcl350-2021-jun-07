package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	@Autowired
	private BusinessService businessService;

	@Scheduled(fixedRate = 30_000)
	public void demo() {
		// com.example.service.BusinessService$$EnhancerBySpringCGLIB$$6faaecbc
		// Dynamic Proxy -> 
		System.err.println(businessService.getClass().getName());
		for (var i = 1; i <= 20; ++i) {
			var result = businessService.haveGun();
			System.err.println(String.format("%d produces %s.", i, result));
		}
	}

}
