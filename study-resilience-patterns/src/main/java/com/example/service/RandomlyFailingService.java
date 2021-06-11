package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class RandomlyFailingService {

	public String haveFun() {
		var random = ThreadLocalRandom.current().nextDouble();
		if (random <= 0.9) {
			System.err.println("haveFun() return 'success'.");
			return "success";
		} else if (random <= 0.95) {
			System.err.println("haveFun() return 'failure'.");
			return "failure";
		} else {
			System.err.println("haveFun() throws IllegalStateException.");
			throw new IllegalStateException("This is not legal state!");
		}
	}
}
