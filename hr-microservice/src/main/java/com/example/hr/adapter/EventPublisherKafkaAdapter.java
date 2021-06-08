package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.event.EmployeeEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher<EmployeeEvent> {
    @Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    
	@Override
	public void publish(EmployeeEvent event) {
		try {
			// save the event to mongodb
			kafkaTemplate.send("hr", objectMapper.writeValueAsString(event));
			// remove the event from mongodb
		}catch (JsonProcessingException e) {
			System.err.println("Error in converting object to json: "+e.getMessage());
		}
		
	}

}
