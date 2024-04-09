package com.example.demo.OpenWeatherMap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class OpenWeatherForecast {
    @JsonProperty("cod")
    private String cod;
    @JsonProperty("message")
    private int message;
    @JsonProperty("cnt")
    private int cnt;
    @JsonProperty("list")
    private List<OpenForecastData> list;
    @JsonProperty("city")
    private OpenCity city;

    // Getters and setters
    // Note: You can generate these using your IDE or lombok library

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<OpenForecastData> getList() {
        return list;
    }

    public void setList(List<OpenForecastData> list) {
        this.list = list;
    }
    public OpenCity getCity() {
        return city;
    }
    public void setCity(OpenCity city) {
        this.city = city;
    }
}