package com.example.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class StockmarketRabbitConsumerService {

	@RabbitListener(queues = "stockmarketq")
	public void listenMarketEvent(String event) {
		System.err.println("New event has arived from Rabbit: "+event);
	}
}
