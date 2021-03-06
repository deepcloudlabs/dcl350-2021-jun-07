package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class BusinessService {
	@Autowired
	private RandomlyFailingService randomlyFailingService;

	@Retry(name = "retry1", fallbackMethod = "haveGunRetryFallback")
	// @RateLimiter(name = "ratelimiter1", fallbackMethod =
	// "haveGunRateLimiterFallback")
	// @Bulkhead(name = "bulkhead1", type = Type.SEMAPHORE, fallbackMethod =
	// "haveGunBulkheadFallback")
	@CircuitBreaker(name = "circuitbreaker1", fallbackMethod = "haveGunCircuitBreakerFallback")
	public String haveGun() {
		return randomlyFailingService.haveFun();
	}

	public String haveGunRetryFallback(Exception e) {
		return "retry-fallback";
	}

	public String haveGunRateLimiterFallback(Exception e) {
		return "ratelimiter-fallback";
	}

	public String haveGunBulkheadFallback(Exception e) {
		return "bulkhead-fallback";
	}

	public String haveGunCircuitBreakerFallback(Exception e) {
		return "circuitbreaker-fallback";
	}
}
