package com.example.binance.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BinanceSyncRestOverHttpClient {

	private static final String binanceHttpUrl = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger counter = new AtomicInteger(0);
	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(binanceHttpUrl))
				                 .header("Accept","application/json")
				                 .build();
		var start = System.currentTimeMillis();
		for(var i=0;i<10;++i) {
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			      .thenAcceptAsync( response -> {
			    	  System.err.println(Thread.currentThread().getName()+": "+response.body());
			    	  if(counter.incrementAndGet()==10) {
			    		  var stop = System.currentTimeMillis();
			    		  System.err.println("Duration: "+(stop-start)+" ms.");			    		  
			    	  }
			      });
		}
		TimeUnit.SECONDS.sleep(5);
		System.err.println("Done.");
	}

}
