package com.example.lottery.application;

import java.util.List;

public interface LotteryApplication {
	List<Integer> draw();

	List<List<Integer>> draw(int column);
}
