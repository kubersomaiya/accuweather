package com.example.demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.OpenWeatherMap.OpenweathermapUtil;


@Configuration
public class AccuWeatherConfig {

    @Bean
    CommandLineRunner commandLineRunner(LocationDetailsRepository repository, WeatherForecastDetailsRepository actRepository ,WeatherService weatherService ) {
        return args -> {
            try {
                // LocationDetails nobj1 = new LocationDetails(
                //         "Maharashtra",
                //         "Mumbai",
                //         "");
                // LocationDetails nobj2 = new LocationDetails(
                //         "Maharashtra",
                //         "Thane",
                //         "");
                // LocationDetails nobj3 = new LocationDetails(
                //         "Maharashtra",
                //         "Nagpur",
                //         "");
                // LocationDetails nobj4 = new LocationDetails(
                //         "Maharashtra",
                //         "Washim",
                //         "");
                // List<LocationDetails> dummyData = List.of(nobj1, nobj2, nobj3, nobj4);
                // repository.saveAll(dummyData);
                // weatherService.saveOrUpdateLocationData();
                weatherService.callForecastApiForAllData();

            } catch (Exception e) {
                // Handle the exception here, you can log it or perform any other necessary
                // action
                e.printStackTrace();
            }
        };
    }
}
