package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.demo.ForecastDetails.DailyForecast;
import com.example.demo.ForecastDetails.MainForecast;
import com.example.demo.exceptions.NoForecastDetailsFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AccuweatherUtil implements WeatherInterface {

    @Value("${accuweather.api.key}")
    private String apiKey;

    @Autowired
    private LocationDetailsRepository accuWeatherRepository;

    private static final Logger LOGGER = Logger.getLogger(AccuweatherUtil.class.getName());

    @Override
    public String getLocationKey(String district) {
        String locationKey = "";
        try {
            String accuWeatherUrl = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=";
            String arg = "&q=";
            StringBuilder sb = new StringBuilder();
            sb.append(accuWeatherUrl);
            sb.append(apiKey);
            sb.append(arg);
            sb.append(district);

            String locationUrl = sb.toString();

            URL urlObj = new URL(locationUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                locationKey = response.substring(21, 27);
            } else {
                throw new NoForecastDetailsFoundException("Error in fetching Location Details" + responseCode);
                // System.out.println("Error in fetching Location Details");
            }
        } catch (IOException e) {
            throw new NoForecastDetailsFoundException("Failed to hit API " + e.getMessage());
            // System.out.println(e.getMessage());
        }
        LOGGER.info("Location Key successfully generated and returned");
        return locationKey;
        
    }
    
    @Override
    public List<WeatherForecastDetails> getForecastDetails(String locationKey) {
        List<LocationDetails> allData = accuWeatherRepository.findAll();
        List<WeatherForecastDetails> forecastsList = new ArrayList<>();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("http://dataservice.accuweather.com/forecasts/v1/daily/5day/");
            sb.append(locationKey);
            sb.append("?apikey=");
            sb.append(apiKey);

            String forecastUrl = sb.toString();
            
            URL urlObj = new URL(forecastUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                
                String jsonData = response.toString();
                ObjectMapper objectMapper = new ObjectMapper();
                MainForecast mainForecast = objectMapper.readValue(jsonData, MainForecast.class);
                List<DailyForecast> dailyForecasts = mainForecast.getDailyForecasts();
                
                if (dailyForecasts != null) {
                    for (DailyForecast forecast : dailyForecasts) {
                        String date = forecast.getDate().substring(0, 10);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate forecastDate = LocalDate.parse(date, formatter);
                        Double minTempFah = forecast.getTemperature().getMinimum().getValue();
                        Double maxTempFah = forecast.getTemperature().getMaximum().getValue();
                        Double minTempCel =  Math.floor((minTempFah - 32) * 5 / 9);
                        Double maxTempCel =  Math.ceil((maxTempFah - 32) * 5 / 9);
                        System.out.println("--------------------");
                        String dayForecast = forecast.getDay().getIconPhrase();
                        String nightForecast = forecast.getNight().getIconPhrase();
                        String district = "";
                        String state = "";
                        for (LocationDetails accuweather : allData) {
                            if (accuweather.getAccuweather_locationKey().equals(locationKey)) {
                                district = accuweather.getDistrict();
                                state = accuweather.getState();
                                // break;
                            }
                        }
                        WeatherForecastDetails obj = new WeatherForecastDetails(forecastDate, minTempCel, maxTempCel,
                        dayForecast,
                        nightForecast, district, state, locationKey);
                        forecastsList.add(obj);
                    }
                }
            } else {
                throw new NoForecastDetailsFoundException("Error in fetching Forecast Details ..." + responseCode);
                // System.out.println("Error in fetching Forecast Details ..."+ responseCode);
            }
        } catch (IOException e) {
            throw new NoForecastDetailsFoundException("Failed to hit API" + e.getMessage());
            // System.out.println(e.getMessage());
        }
        LOGGER.info("Forecast Details successfully generated and returned");
        return forecastsList;
    }

}
