package com.example.lottery.application;

import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.RandomNumberService;

public class LotteryApplication {

	public static void main(String[] args) {
		StandardLotteryService lotteryService = new StandardLotteryService();
		ServiceLoader<RandomNumberService> services = ServiceLoader.load(RandomNumberService.class);
		services.forEach( randomNumberService -> {
			System.err.println(randomNumberService.getClass().getName());
			lotteryService.setRandomNumberService(randomNumberService);			
		});
		
		lotteryService.draw(10).forEach(System.err::println);

	}

}
