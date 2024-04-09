package com.example.demo.OpenWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord {
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;

    // Getters and setters
}
