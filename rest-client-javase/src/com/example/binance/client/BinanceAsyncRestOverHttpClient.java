package com.example.binance.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BinanceAsyncRestOverHttpClient {

	private static final String binanceHttpUrl = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(binanceHttpUrl))
				                 .header("Accept","application/json")
				                 .build();
		var start = System.currentTimeMillis();
		for(var i=0;i<10;++i) {
			var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
			System.out.println(response);
		}
		var stop = System.currentTimeMillis();
		System.err.println("Duration: "+(stop-start)+" ms.");
	}

}
