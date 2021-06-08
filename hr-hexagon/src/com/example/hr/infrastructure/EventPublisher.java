package com.example.hr.infrastructure;

public interface EventPublisher<E> {
	void publish(E event);
}
