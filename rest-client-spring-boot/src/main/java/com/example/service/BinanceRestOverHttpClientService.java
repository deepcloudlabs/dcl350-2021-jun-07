package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.boundary.Ticker;
import com.example.lottery.application.LotteryApplication;

@SuppressWarnings("deprecation")
@Service
public class BinanceRestOverHttpClientService {
	@Value("${binance.http.url}")
	private String binanceHttpUrl;
	@Autowired
	private LotteryApplication lotteryApplication;

	//@Scheduled(fixedRate = 3_000)
	public void callBinance() {
		var restTemplate = new RestTemplate();
		var ticker = restTemplate.getForEntity(binanceHttpUrl, Ticker.class).getBody();
		System.err.println(ticker);
	}

	//@Scheduled(fixedRate = 1_000)
	public void asyncCallBinance() {
		var restTemplate = new AsyncRestTemplate();
		restTemplate.getForEntity(binanceHttpUrl, Ticker.class)
		            .addCallback(
		            		(response) ->{
		            			System.out.println(Thread.currentThread().getName()+": "+response.getBody());
		            		},  // success : listener/observer method
		            		(error)->{
		            			System.err.println("Error has occured: "+error.getMessage());
		            		} // fail: listener/observer method
		            );
	}
	
	//@Scheduled(fixedRate = 1)
	public void callAsyncMethodOfLotteryApplication() {
		System.err.println("callAsyncMethodOfLotteryApplication()");
		lotteryApplication.draw(2)
		                  .thenAccept( numbers -> numbers.forEach(System.out::println) );
	}
	//@Scheduled(fixedRate = 1)
	public void callLotteryApplication() {
		System.err.println("callLotteryApplication()");
		System.err.println(lotteryApplication.draw());
	}
	
	
}
