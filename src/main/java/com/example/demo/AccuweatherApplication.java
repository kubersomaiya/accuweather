package com.example.demo;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccuweatherApplication {

	private static final Logger LOGGER = Logger.getLogger(AccuweatherApplication.class.getName());
	@SuppressWarnings("null")
	public static void main(String[] args)  {
		SpringApplication.run(AccuweatherApplication.class, args);
		LOGGER.info("smooth , application executed successfully");
	}


}
