package com.example.lottery.application.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.lottery.application.LotteryApplication;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryApplication implements LotteryApplication {
	@Autowired
	private RandomNumberService randomNumberService;
	@Value("${lottery.min}")
	private int lotteryMin;
	@Value("${lottery.max}")
	private int lotteryMax;
	@Value("${lottery.size}")
	private long lotterySize;
	
	@Override
	public List<Integer> draw() {
		return IntStream.generate(() -> randomNumberService.generate(lotteryMin, lotteryMax))
				        .distinct()
				        .limit(lotterySize)
				        .sorted()
				        .boxed()
				        .collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> draw())
				        .collect(Collectors.toList());
	}

}
