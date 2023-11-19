package com.checkyou.shinhansec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShinhansecApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShinhansecApplication.class, args);
	}

}
