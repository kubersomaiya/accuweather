package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccuWeatherController {

    @Autowired  private WeatherService weatherService;

    
    @GetMapping("/api/weather/get-forecast-data")
    public List<WeatherForecastDetailsDTO> getForecastDataFiltered(@RequestParam(required = false) String state,
            @RequestParam(required = false) String district) {

        if (state != null && district != null) {
            return weatherService.getForecastDataByDistrict(state, district);
        } else {
            return weatherService.getAllForecastData();
        }

    }
}
