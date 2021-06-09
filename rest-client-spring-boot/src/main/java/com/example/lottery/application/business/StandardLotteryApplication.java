package com.example.lottery.application.business;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.lottery.application.LotteryApplication;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryApplication implements LotteryApplication {
	@Autowired
	private RandomNumberService randomNumberService;
	
	@Override
	public List<Integer> draw() {
		try { TimeUnit.SECONDS.sleep(2); } catch (Exception e) {};
		return IntStream.generate(() -> randomNumberService.generate(1, 60))
				        .distinct()
				        .limit(6)
				        .sorted()
				        .boxed()
				        .collect(Collectors.toList());
	}

	@Override
	@Async
	public CompletableFuture<List<List<Integer>>> draw(int column) {
		return CompletableFuture.supplyAsync(() ->{
			try { TimeUnit.SECONDS.sleep(2); } catch (Exception e) {};
			return IntStream.range(0, column)
			        .mapToObj(i -> draw())
			        .collect(Collectors.toList());
		});		
	}

}
