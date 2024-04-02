package com.example.demo;

import java.time.LocalDate;

public class WeatherForecastDetailsDTO {
    private Long id;
    private LocalDate date;
    private Double minTemp;
    private Double maxTemp;
    private String dayForecast;
    private String nightForecast;
    private String district;
    private String state;
    private String accuLocationKey;

    // Constructors
    public WeatherForecastDetailsDTO() {
        // Default constructor
    }

    public WeatherForecastDetailsDTO(LocalDate date, Double minTemp, Double maxTemp, String dayForecast,
            String nightForecast, String district, String state, String accuLocationKey) {
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.dayForecast = dayForecast;
        this.nightForecast = nightForecast;
        this.district = district;
        this.state = state;
        this.accuLocationKey = accuLocationKey;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getDayForecast() {
        return dayForecast;
    }

    public void setDayForecast(String dayForecast) {
        this.dayForecast = dayForecast;
    }

    public String getNightForecast() {
        return nightForecast;
    }

    public void setNightForecast(String nightForecast) {
        this.nightForecast = nightForecast;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccuLocationKey() {
        return accuLocationKey;
    }

    public void setAccuLocationKey(String accuLocationKey) {
        this.accuLocationKey = accuLocationKey;
    }
}
