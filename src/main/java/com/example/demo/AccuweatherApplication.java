package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccuweatherApplication {

	@SuppressWarnings("null")
	public static void main(String[] args)  {
		SpringApplication.run(AccuweatherApplication.class, args);
		System.out.println("smooth");
	}


}
