package com.example.demo;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.demo.OpenWeatherMap.OpenweathermapUtil;

@Component
public class WeatherServiceFactory {

    @Autowired private LocationDetailsRepository locationDetailsRepo;
    @Autowired private OpenweathermapUtil openweathermapUtil;
    @Autowired private AccuweatherUtil accuweatherUtil;


    public List<WeatherForecastDetails> createWeatherService(String argument) throws ParseException {
        List<WeatherForecastDetails> forecastData = null;
        if (isLocationKey(argument)) {
            forecastData = accuweatherUtil.getForecastDetails(argument);
        } else if (isDistrict(argument)) {
            forecastData = openweathermapUtil.getForecastDetails(argument);
        } else {
            throw new IllegalArgumentException("Invalid argument: " + argument);
        }
        return forecastData;
    }
    

    private static boolean isLocationKey(String argument) {

        return argument.matches("\\d+"); 
    }

    private boolean isDistrict(String argument) {
        List<String> knownDistricts = locationDetailsRepo.getAllDistricts(); // Implement a method to fetch known districts
        return knownDistricts.contains(argument);
    }
}
