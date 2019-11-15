package com.meli.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GalaxyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyApiApplication.class, args);
	}

}

