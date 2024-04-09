package com.example.demo;
import java.util.List;

public interface WeatherInterface {
    public String getLocationKey(String district);
    public List<WeatherForecastDetails> getForecastDetails(String locationKey);
}
