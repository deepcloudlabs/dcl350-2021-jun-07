package com.example.service;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.example.event.StockPriceChangedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockmarketRabbitService {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	@EventListener
	public void listenEvent(StockPriceChangedEvent event) {
		try {
			var jsonEvent = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend("stockmarketex", null, jsonEvent);
		} catch (IOException e) {
			System.err.println("Error: "+e.getMessage());
		}
	}
}
