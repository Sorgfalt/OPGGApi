package com.opgg.opggapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OpggApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpggApiApplication.class, args);
	}

}
