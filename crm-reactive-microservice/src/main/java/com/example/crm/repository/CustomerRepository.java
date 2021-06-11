package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.domain.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
	Flux<Customer> findByLastName(String lastName);

	Mono<Customer> findByEmail(String email);

	@Query("{}")
	Flux<Customer> findAll(PageRequest page);
}
