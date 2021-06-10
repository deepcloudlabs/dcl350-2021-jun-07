package com.example.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.event.StockPriceChangedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockmarketKafkaService {
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	@EventListener
	public void listenEvent(StockPriceChangedEvent event) {
		try {
			var jsonEvent = objectMapper.writeValueAsString(event);
			kafkaTemplate.send("stockmarket", jsonEvent);
		} catch (IOException e) {
			System.err.println("Error: "+e.getMessage());
		}
	}
}
