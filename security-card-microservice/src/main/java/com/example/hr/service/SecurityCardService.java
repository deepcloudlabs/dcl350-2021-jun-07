package com.example.hr.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityCardService {

	@KafkaListener(topics = "hr")
	public void listenEmployeeEvent(String event) {
		System.err.println("Event is consumed: "+event);
	}
}
