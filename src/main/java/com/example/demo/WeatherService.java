package com.example.demo;

import com.example.demo.OpenWeatherMap.OpenweathermapUtil;
import com.example.demo.exceptions.NoForecastDetailsFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        locationRepo.saveAll(allData);
    }

    public List<WeatherForecastDetails> getForecastData(String locationKey) throws ParseException {
        return accuWeatherUtil.getForecastDetails(locationKey);
    }

    public void callForecastApiForAllData() {
        forecastRepo.deleteForecastDataGreaterThanToday();
        List<LocationDetails> allData = locationRepo.findAll();
        List<WeatherForecastDetails> forecastData = new ArrayList<>();
        List<WeatherForecastDetails> finalData = new ArrayList<>();
        for (LocationDetails accuweather : allData) {
            try {
                forecastData = getForecastData(accuweather.getAccuweather_locationKey());
                for (WeatherForecastDetails data : forecastData) {
                    finalData.add(data);
                }

            } catch (Exception e) {
                throw new NoForecastDetailsFoundException(e.getMessage());
            }
        }
        forecastRepo.saveAll(finalData);
    }

    
    public List<WeatherForecastDetails> getOpenweatherForecast(String district){
        return openweathermapUtil.getForecastDetails(district);
    }
    
    public void callForecastApiForAllDataOpenWeather() {
        forecastRepo.deleteForecastDataGreaterThanToday();
        List<LocationDetails> allData = locationRepo.findAll();
        List<WeatherForecastDetails> forecastData = new ArrayList<>();
        List<WeatherForecastDetails> finalData = new ArrayList<>();
        for (LocationDetails accuweather : allData) {
            try {
                forecastData = getOpenweatherForecast(accuweather.getDistrict());
                for (WeatherForecastDetails data : forecastData) {
                    finalData.add(data);
                }
                
            } catch (Exception e) {
                throw new NoForecastDetailsFoundException(e.getMessage());
            }
        }
        forecastRepo.saveAll(finalData);
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void saveOrUpdateLocationData() {
        callAccuweatherLocationApiForAllData();
    }
    
    @Scheduled(cron = "0 0 6 * * *")
    public void saveOrUpdateAccuweatherForecastData() {
        callForecastApiForAllData();
    }
    @Scheduled(cron = "0 0 6 * * *")
    public void saveOrUpdateOpenWeatherForecastData() {
        callForecastApiForAllDataOpenWeather();
    }
}
