package com.example.demo.OpenWeatherMap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class OpenForecastData {
    @JsonProperty("dt")
    private long dt;
    @JsonProperty("main")
    private OpenMain main;
    @JsonProperty("weather")
    private List<Weather> weather;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("visibility")
    private int visibility;
    @JsonProperty("pop")
    private int pop;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("dt_txt")
    private String dtTxt;

    // Getters and setters
    // Note: You can generate these using your IDE or lombok library

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public OpenMain getMain() {
        return main;
    }

    public void setMain(OpenMain main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}