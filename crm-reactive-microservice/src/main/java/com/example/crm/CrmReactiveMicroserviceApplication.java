package com.example.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Add a customer
// curl -X POST "http://localhost:9400/customers" -d "{\"identity\": \"1\", \"firstName\": \"jack\", \"lastName\": \"bauer\", \"email\": \"jack@example.com\"}" -H "Content-Type: application/json" -H "Accept: application/json" 

// Find customer by identity
// curl -X GET "http://localhost:9400/customers/1"

// Pagination
// curl -X GET "http://localhost:9400/customers?page=0&size=10"

@SpringBootApplication
public class CrmReactiveMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmReactiveMicroserviceApplication.class, args);
	}

}
