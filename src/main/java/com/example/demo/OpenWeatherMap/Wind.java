package com.example.demo.OpenWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Wind {
    @JsonProperty("speed")
    private double speed;
    @JsonProperty("deg")
    private int deg;
    @JsonProperty("gust")
    private double gust;

    // Getters and setters
    // Note: You can generate these using your IDE or lombok library
}