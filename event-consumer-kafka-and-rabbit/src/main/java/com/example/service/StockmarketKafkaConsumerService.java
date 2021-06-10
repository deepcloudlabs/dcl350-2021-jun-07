package com.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockmarketKafkaConsumerService {

	@KafkaListener(topics = "stockmarket", groupId = "stockmarket")
	public void listenMarketEvent(String event) {
		System.err.println("New event has arived from Kafka: "+event);
	}
}
