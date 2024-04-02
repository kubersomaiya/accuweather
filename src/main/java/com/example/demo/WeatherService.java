package com.example.demo;

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
    private LocationDetailsRepository accuWeatherRepository;
    @Autowired
    private WeatherForecastDetailsRepository actRepository;
    @Autowired
    private AccuweatherUtil accuWeatherUtil;
    @Autowired
    private WeatherForecastDetailsConverter converter;

    public List<WeatherForecastDetailsDTO> getAllForecastData() {
        return converter.convert(actRepository.findAll());
    }

    public List<WeatherForecastDetailsDTO> getForecastDataByDistrict(String state, String district) {

        return converter.convert(actRepository.filterActualForecastData(state, district));
    }

    public List<LocationDetails> getDistrictsWithLocationKeyNull() {
        return accuWeatherRepository.findDistrictsWithLocationKeyNull();
    }

    public String getLocationKey(String district) {
        return accuWeatherUtil.getAccuweatherLocationKey(district);
    }

    public void callAccuweatherLocationApiForAllData() {
        List<LocationDetails> allData = accuWeatherRepository.findDistrictsWithLocationKeyNull();
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
        accuWeatherRepository.saveAll(allData);
    }

    public List<WeatherForecastDetails> getForecastData(String locationKey) throws ParseException {
        return accuWeatherUtil.getForecastDetails(locationKey);
    }

    public void callForecastApiForAllData() {
        List<LocationDetails> allData = accuWeatherRepository.findAll();
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
        actRepository.saveAll(finalData);
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void saveOrUpdateLocationData() {
        callAccuweatherLocationApiForAllData();

    }

    @Scheduled(cron = "0 0 6 * * *")
    public void saveOrUpdateForecastData() {
        actRepository.deleteForecastDataGreaterThanToday();
        callForecastApiForAllData();
    }

}
