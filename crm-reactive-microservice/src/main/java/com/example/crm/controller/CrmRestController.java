package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.domain.Customer;
import com.example.crm.service.CrmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@Validated
@CrossOrigin
public class CrmRestController {
	@Autowired
	private CrmService crmService;
	
	@GetMapping("{identity}")
	public Mono<Customer> findByIdentity(@PathVariable String identity) {
		return crmService.findCustomerByIdentity(identity);
	}
	
	@GetMapping
	public Flux<Customer> findAllByPage(@RequestParam int page,@RequestParam int size) {
		return crmService.findCustomersByPage(page,size);
	}
	
	@PostMapping
	public Mono<Customer> acquireCustomer(@RequestBody Customer customer){
		return crmService.addCustomer(customer);
	}
	
	@PutMapping("{identity}")
	public Mono<Customer> acquireCustomer(@PathVariable String identity,@RequestBody Customer customer){
		return crmService.updateCustomer(identity, customer);
	}
	
	
	@DeleteMapping("{identity}")
	public Mono<Customer> releaseCustomerByIdentity(@PathVariable String identity) {
		return crmService.releaseCustomerByIdentity(identity);
	}
}
