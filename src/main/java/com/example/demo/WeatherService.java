package com.example.demo;

import com.example.demo.OpenWeatherMap.OpenweathermapUtil;
import com.example.demo.exceptions.NoForecastDetailsFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private LocationDetailsRepository locationRepo;
    @Autowired
    private WeatherForecastDetailsRepository forecastRepo;
    @Autowired
    private AccuweatherUtil accuWeatherUtil;
    @Autowired
    private WeatherForecastDetailsConverter converter;
    @Autowired
    private OpenweathermapUtil openweathermapUtil;
    @Autowired
    private WeatherServiceFactory weatherServiceFactory;

    private static final Logger LOGGER = Logger.getLogger(WeatherService.class.getName());

    public List<WeatherForecastDetailsDTO> getAllForecastData() {
        return converter.convert(forecastRepo.findAll());
    }

    public List<WeatherForecastDetailsDTO> getForecastDataByDistrict(String state, String district) {

        return converter.convert(forecastRepo.filterActualForecastData(state, district));
    }

    public List<LocationDetails> getDistrictsWithLocationKeyNull() {
        return locationRepo.findDistrictsWithLocationKeyNull();
    }

    public String getLocationKey(String district) {
        return accuWeatherUtil.getLocationKey(district);
    }

    public void callAccuweatherLocationApiForAllData() {
        List<LocationDetails> allData = locationRepo.findDistrictsWithLocationKeyNull();
        for (LocationDetails accuweather : allData) {
            System.out.println("Processing district: " + accuweather.getDistrict());
            try {
                String locationKey = getLocationKey(accuweather.getDistrict());
                System.out.println("Location key retrieved: " + locationKey);
                accuweather.setAccuweather_locationKey(locationKey);
            } catch (NoForecastDetailsFoundException e) {

                System.err.println("Error fetching location key for district " + accuweather.getDistrict() + ": "
                        + e.getMessage());

                continue;
            } catch (Exception e) {

                System.err.println(
                        "Unexpected error processing district " + accuweather.getDistrict() + ": " + e.getMessage());

                continue;
            }
        }
        LOGGER.info("Called API for locationKey for all districts and saved in db");
        locationRepo.saveAll(allData);
    }

    public void callForecastApiForAllData() {
        forecastRepo.deleteForecastDataGreaterThanToday();
        List<LocationDetails> allData = locationRepo.findAll();
        List<WeatherForecastDetails> finalData = new ArrayList<>();
        List<WeatherForecastDetails> forecastData = new ArrayList<>();
        for (LocationDetails accuweather : allData) {
            try {
                // call below method to hit openweathermap API
                forecastData = weatherServiceFactory.createWeatherService(accuweather.getDistrict());
                
                // call below method to hit accuweather API
                // forecastData = weatherServiceFactory.createWeatherService(accuweather.getAccuweather_locationKey());
                finalData.addAll(forecastData);
            } catch (Exception e) {
                throw new NoForecastDetailsFoundException(e.getMessage());
            }
        }
        LOGGER.info("Called API for forecast details using Accuweather for all districts and saved in db");
        forecastRepo.saveAll(finalData);
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void saveOrUpdateLocationData() {
        callAccuweatherLocationApiForAllData();
        LOGGER.info("Scheduler to save Location Data executed");
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void saveOrUpdateAccuweatherForecastData() {
        callForecastApiForAllData();
        LOGGER.info("Scheduler to save Forecast Data using Accuweather executed");
    }
}
