package com.example.demo.OpenWeatherMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.demo.LocationDetailsRepository;
import com.example.demo.WeatherForecastDetails;
import com.example.demo.WeatherInterface;
import com.example.demo.exceptions.NoForecastDetailsFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OpenweathermapUtil implements WeatherInterface {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Autowired
    private LocationDetailsRepository ldRepo;


    private static final Logger LOGGER = Logger.getLogger(OpenweathermapUtil.class.getName());
    // below method not required!!
    @Override
    public String getLocationKey(String district) {
        return "location-key";
    }

    @Override
    public List<WeatherForecastDetails> getForecastDetails(String district) {
        List<WeatherForecastDetails> forecastsList = new ArrayList<>();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("http://api.openweathermap.org/data/2.5/forecast?q=");
            sb.append(district);
            sb.append("&appid=");
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
                ObjectMapper mapper = new ObjectMapper();

                OpenWeatherForecast weatherForecast = mapper.readValue(jsonData, OpenWeatherForecast.class);
                OpenCity city = weatherForecast.getCity();
                String locationKey = String.valueOf(city.getId());
                String state = ldRepo.findStateWithDistrict(district);
                String dayForecast = "";
                String nightForecast = "";
                LocalDate forecastDate = LocalDate.now();
                Double minTemp = 0.0;
                Double maxTemp = 0.0;
                List<OpenForecastData> forecastDataList = weatherForecast.getList();
                List<String> dayForecasts = new ArrayList<>();
                List<String> nightForecasts = new ArrayList<>();
                List<LocalDate> dates = new ArrayList<>();
                List<Double> minTemps = new ArrayList<>();
                List<Double> maxTemps = new ArrayList<>();

                for (OpenForecastData forecastData : forecastDataList) {
                    String date = forecastData.getDtTxt().substring(0, 10);
                    String time = forecastData.getDtTxt().substring(11, 13);
                    int hour = Integer.parseInt(time);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    forecastDate = LocalDate.parse(date, formatter);
                    OpenMain main = forecastData.getMain();
                    minTemp = Math.floor(main.getTempMin() - 273.15);
                    maxTemp = Math.ceil(main.getTempMax() - 273.15);
                    List<Weather> weath = forecastData.getWeather();

                    for (Weather data : weath) {
                        if ((hour == 9)) {
                            dayForecasts.add(data.getDescription());
                            dates.add(forecastDate);
                            minTemps.add(minTemp);
                            maxTemps.add(maxTemp);
                        } else if ((hour == 21)) {
                            nightForecasts.add(data.getDescription());
                        }
                    }
                }
                for (int i = 0; i < dayForecasts.size(); i++) {
                    dayForecast = dayForecasts.get(i);
                    nightForecast = nightForecasts.get(i);
                    forecastDate = dates.get(i);
                    minTemp = minTemps.get(i);
                    maxTemp = maxTemps.get(i);
                    WeatherForecastDetails obj = new WeatherForecastDetails(forecastDate, minTemp, maxTemp, dayForecast,
                            nightForecast, district, state, locationKey);
                    forecastsList.add(obj);
                }
            } else {
                throw new NoForecastDetailsFoundException("Error in fetching Forecast Details ... " + responseCode);
            }
        } catch (IOException e) {
            throw new NoForecastDetailsFoundException("Failed to hit API " + e.getMessage());
        }
        LOGGER.info("OpenWeather API successfully executed and list is returned");
        return forecastsList;
    }

}