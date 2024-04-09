package com.example.demo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather_forecast_details")
public class WeatherForecastDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "min_temp", nullable = false)
    private Double minTemp;

    @Column(name = "max_temp", nullable = false)
    private Double maxTemp;

    @Column(name = "day_forecast", nullable = false)
    private String dayForecast;

    @Column(name = "night_forecast", nullable = false)
    private String nightForecast;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "accu_locationkey", nullable = false)
    private String accuLocationKey;

    public WeatherForecastDetails() {
        // Default constructor
    }

    public WeatherForecastDetails(LocalDate date, Double minTemp, Double maxTemp, String dayForecast, String nightForecast,
                              String district, String state, String accuLocationKey) {
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.dayForecast = dayForecast;
        this.nightForecast = nightForecast;
        this.district = district;
        this.state = state;
        this.accuLocationKey = accuLocationKey;
    }

    public WeatherForecastDetails(LocalDate date, Double minTemp, Double maxTemp,
                              String district, String state, String accuLocationKey) {
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.district = district;
        this.state = state;
        this.accuLocationKey = accuLocationKey;
    }

    // Getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMinTemp() {
        return minTemp;
    }
    public Long getId() {
        return id;
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
