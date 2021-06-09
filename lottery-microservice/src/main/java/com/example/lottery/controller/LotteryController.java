package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.application.LotteryApplication;

@RestController
@RequestScope
@RequestMapping("/numbers")
public class LotteryController {
	@Autowired
	private LotteryApplication lotteryApplication;
	
	// http://localhost:3200/lottery/api/v1/numbers?column=10
	@GetMapping(params = "column")
	public List<List<Integer>> getLotteryNumbers(
			@RequestParam(required = false, defaultValue = "1") int column){
		return lotteryApplication.draw(column);
	}
}
