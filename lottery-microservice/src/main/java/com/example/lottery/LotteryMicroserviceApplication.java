package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// mvn clean install spring-boot:repackage
// jar xvf lottery-microservice-0.0.1-SNAPSHOT.jar BOOT-INF/classes/application.properties
// java -cp . -jar lottery-microservice-0.0.1-SNAPSHOT.jar
@SpringBootApplication
public class LotteryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryMicroserviceApplication.class, args);
	}

}
