package com.example.random.service;

public interface RandomNumberService {
	int generate(int min, int max);

	default int generate(int max) {
		return generate(1, max);
	};
}
