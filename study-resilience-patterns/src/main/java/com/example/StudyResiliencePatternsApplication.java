package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudyResiliencePatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyResiliencePatternsApplication.class, args);
	}

}
