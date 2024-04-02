package com.example.demo.ForecastDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
public class MainForecast {
    @JsonProperty("Headline")
    private Headline headline;
    @JsonProperty("DailyForecasts")
    public List<DailyForecast> dailyForecasts;

    // Getters and setters
    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }
}