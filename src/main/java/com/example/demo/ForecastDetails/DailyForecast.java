package com.example.demo.ForecastDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class DailyForecast {
    @JsonProperty("Date")
    public String Date;
    @JsonProperty("Temperature")
    public Temperature Temperature;
    @JsonProperty("Day")
    public Day Day;
    @JsonProperty("Night")
    public Night Night;
    public String district;

    // Getters and setters
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(Temperature temperature) {
        Temperature = temperature;
    }

    public Day getDay() {
        return Day;
    }

    public void setDay(Day day) {
        Day = day;
    }

    public Night getNight() {
        return Night;
    }

    public void setNight(Night night) {
        Night = night;
    }

    public String getDistrict(){
        return district;
    }
    public String setDistrict(String district){
        this.district = district;
        return district;
    }
}