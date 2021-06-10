package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.StockRepository;

@SpringBootApplication
@SuppressWarnings("unused")
public class StudyEventDrivenProgrammingApplication implements ApplicationRunner{
	@Autowired
	private StockRepository stockRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(StudyEventDrivenProgrammingApplication.class, args);
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		/*
		stockRepository.save(new Stock("orcl", 123.45)); 
		stockRepository.save(new Stock("msft", 143.67)); 
		stockRepository.save(new Stock("ibm", 45.89)); 
		*/
	}

}
