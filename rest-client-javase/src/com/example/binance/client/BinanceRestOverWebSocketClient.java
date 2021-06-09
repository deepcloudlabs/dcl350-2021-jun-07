package com.example.binance.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class BinanceRestOverWebSocketClient {

	private static final String binanceWsUrl = "wss://stream.binance.com:9443/ws/btcusdt@trade";
	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient()
				               .newWebSocketBuilder()
				               .buildAsync(URI.create(binanceWsUrl), new BinanceWebSocketListener());
		
		TimeUnit.SECONDS.sleep(30);
		client.cancel(true);
		System.err.println("Done.");
	}

}


class BinanceWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to binance server!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		webSocket.request(1);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from binance server!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("Error: "+error.getMessage());
		Listener.super.onError(webSocket, error);
	}
	
}