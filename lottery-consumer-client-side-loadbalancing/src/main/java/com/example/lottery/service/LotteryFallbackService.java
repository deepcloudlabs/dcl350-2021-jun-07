package com.example.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LotteryFallbackService implements LotteryService {

	@Override
	public List<List<Integer>> getir(int column) {
		return List.of(List.of(1, 2, 3, 4, 5, 6), List.of(4, 8, 15, 16, 23, 42));
	}

}
