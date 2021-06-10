package com.example.lottery.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "lottery")
public interface LotteryService {
	@GetMapping("/lottery/api/v1/numbers")
	@Retry(name="lottery", fallbackMethod = "fallbackGetir")
	List<List<Integer>> getir(@RequestParam int column);
	
	default List<List<Integer>> fallbackGetir(@RequestParam int column,Exception e){
		return List.of(List.of(1, 2, 3, 4, 5, 6), List.of(4, 8, 15, 16, 23, 42));
	}
	
}
