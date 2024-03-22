package com.example.demo.accuweather;

// DailyForecast.java
public class DailyForecast {
    private Temperature temperature;
    private Day day;
    private Night night;

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Night getNight() {
        return night;
    }

    public void setNight(Night night) {
        this.night = night;
    }
}
