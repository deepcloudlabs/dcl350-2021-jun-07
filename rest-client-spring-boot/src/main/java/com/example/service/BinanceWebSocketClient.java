package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.boundary.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BinanceWebSocketClient implements WebSocketHandler {

	@Autowired
	private WebSocketClient webSocketClient;
	@Value("${binance.ws.url}")
	private String binanceWsUrl;
	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void connectToBinance() {
		webSocketClient.doHandshake(this, binanceWsUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the binance server [" + session.getId() + "]");

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var tradeMessage = message.getPayload().toString();
		var trade = objectMapper.readValue(tradeMessage, Trade.class);
		System.err.println(trade);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err.println("Error has occured: "+exception.getMessage());

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the binance server [" + session.getId() + "]");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
