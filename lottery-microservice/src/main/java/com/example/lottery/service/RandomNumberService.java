package com.example.lottery.service;

public interface RandomNumberService {
	
	default int generate(int max) {
		return generate(1, max);
	};
	
	int generate(int min,int max);
}
