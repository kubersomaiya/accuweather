package com.example.demo.accuweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccuWeatherService {

    @Value("${my.api.key}")
    private String apiKey;

    private final AccuWeatherRepository accuWeatherRepository;

    @Autowired
    public AccuWeatherService(AccuWeatherRepository accuWeatherRepository) {
        this.accuWeatherRepository = accuWeatherRepository;
    }

    public List<Accuweather> getDisticts() {
        return accuWeatherRepository.findAll();
    }

    public void addNewDistrict(Accuweather accuWeatherObj) {

        accuWeatherRepository.save(accuWeatherObj);
        System.out.println(accuWeatherObj);

    }

    // -------------- NEXT TASK --------------
    // 1. create one method to update district wise accuweather locationKey
    // 2. find all districts -> loop through districts -> call accuweather api key
    // function (getLocationKey()) -> store response in respective district (save in
    // POJO) -> END LOOP -> store in DB after all district's key are recieved
    // 3. loop through districts -> get weather details by calling api and save in
    // POJO -> END LOOP -> store in DB
    // use objectmapper instead of substring (okay if not done)
    public String getLocationKey(String district) throws IOException {
        String locationKey = "";
        try {
            String accuWeatherUrl = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=";
            String arg = "&q=";
            // System.out.println(accuWeatherUrl);
            StringBuilder sb = new StringBuilder();
            sb.append(accuWeatherUrl);
            sb.append(apiKey);
            sb.append(arg);
            sb.append(district);

            // System.out.println("url is " +sb);
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
                // System.out.println(response);
                locationKey = response.substring(21, 27);
            } else {
                System.out.println("Error in sending a GET request. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return locationKey;
    }

    public List<Accuweather> callLocationApiForAllData() throws IOException {
        List<Accuweather> allData = getDisticts();
        for (Accuweather accuweather : allData) {
            String key = accuweather.getAccuweather_locationKey();
            String district = accuweather.getDistrict();
            // String locationKey = getLocationKey(district);
            String locationKey = "kuber";
            System.out.println(district);
            System.out.println(locationKey);
            key = locationKey;
            accuweather.setAccuweather_locationKey(locationKey);
        }

        return allData;
    }

    public void getForecastData(String locationKey) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("http://dataservice.accuweather.com/forecasts/v1/daily/1day/");
            sb.append(locationKey);
            sb.append("?apikey=");
            sb.append(apiKey);

            String forecastUrl = sb.toString();

            URL urlObj = new URL(forecastUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode-->");
            System.out.println(responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // BufferedReader in = new BufferedReader(new
                // InputStreamReader(connection.getInputStream()));
                // StringBuilder response = new StringBuilder();
                // String inputLine;
                // while ((inputLine = in.readLine()) != null) {
                // response.append(inputLine);
                // }
                // in.close();
                // System.out.println(response);

                // Assuming you have the JSON data in a String variable named jsonData

                // ObjectMapper objectMapper = new ObjectMapper();
                // MainForecast mainForecast = objectMapper.readValue(jsonData, MainForecast.class);

                // for (DailyForecast forecast : mainForecast.getDailyForecasts()) {
                //     double minTemp = forecast.getTemperature().getMinimum().getValue();
                //     double maxTemp = forecast.getTemperature().getMaximum().getValue();
                //     String dayForecast = forecast.getDay().getIconPhrase();
                //     String nightForecast = forecast.getNight().getIconPhrase();

                //     // Save these values to your database or perform any other desired operation
                // }
            } else {
                System.out.println("Error in sending a GET request. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void callForecastApiForAllData() {

    }

}
