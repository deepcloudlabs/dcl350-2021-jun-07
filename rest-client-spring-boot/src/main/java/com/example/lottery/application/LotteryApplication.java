package com.example.lottery.application;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LotteryApplication {
	List<Integer> draw();

	CompletableFuture<List<List<Integer>>> draw(int column);
}
