package com.example.crm.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.domain.Customer;
import com.example.crm.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmService {
	@Autowired
	private CustomerRepository custRepo;
	
	public Mono<Customer> findCustomerByIdentity(String identity) {
		return custRepo.findById(identity);
	}

	public Flux<Customer> findCustomersByPage(int page, int size) {
		return custRepo.findAll(PageRequest.of(page, size));
	}

	public Mono<Customer> addCustomer(Customer customer) {
		return custRepo.insert(customer);
	}

	public Mono<Customer> updateCustomer(String identity, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<Customer> releaseCustomerByIdentity(String identity) {
		//Spring Data Reactive Mongo --> Publisher
		// Observer -> Callback -> Lambda Expression
		Consumer<Customer> remover = customer -> {
			custRepo.delete(customer);
		};
		var customer = custRepo.findById(identity);
		customer.subscribe(remover);
		return customer;
	}

}
