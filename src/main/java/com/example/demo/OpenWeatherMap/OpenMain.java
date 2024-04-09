package com.example.demo.OpenWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class OpenMain {
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    @JsonProperty("pressure")
    private int pressure;
    @JsonProperty("sea_level")
    private int seaLevel;
    @JsonProperty("grnd_level")
    private int grndLevel;
    @JsonProperty("humidity")
    private int humidity;
    @JsonProperty("temp_kf")
    private double tempKf;

    // Getters and setters
    // Note: You can generate these using your IDE or lombok library
    public void setTemp(double temp) {
        this.temp = temp;
    }
    public double getTemp() {
        return temp;
    }
    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }
    public double getTempMin() {
        return tempMin;
    }
    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
    public double getTempMax() {
        return tempMax;
    }
}