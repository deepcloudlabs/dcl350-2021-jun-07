package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryFeignClientService {
	@Autowired
	private LotteryService lotteryService;
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryMicroservice() {
		System.err.println(lotteryService.getir(10));
	}
}
