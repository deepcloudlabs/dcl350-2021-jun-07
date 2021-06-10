package com.example.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.event.StockPriceChangedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockMarketWebSocketService implements WebSocketHandler {
	private static final Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
	@Autowired
	private ObjectMapper objectMapper;
	
	@EventListener
	public void listenEvent(StockPriceChangedEvent event) {
		System.err.println(Thread.currentThread().getName()+", StockMarketWebSocketService has received the event: "+event);
		//try{ TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
		sessions.forEach((sessionId,session)->{
			try {
				session.sendMessage(new TextMessage(objectMapper.writeValueAsString(event)));
			} catch (IOException e) {
				System.err.println("Error: "+e.getMessage());
			}
		});
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("New connection has arrived: "+session.getId());
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println(message.getPayload().toString());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		sessions.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
