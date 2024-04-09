package com.example.demo.OpenWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Clouds {
    @JsonProperty("all")
    private int all;

    // Getters and setters
    // Note: You can generate these using your IDE or lombok library
}
