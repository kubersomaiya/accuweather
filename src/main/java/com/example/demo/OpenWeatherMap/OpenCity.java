package com.example.demo.OpenWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenCity {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("country")
    private String country;
    @JsonProperty("population")
    private int population;
    @JsonProperty("timezone")
    private int timezone;
    @JsonProperty("sunrise")
    private long sunrise;
    @JsonProperty("sunset")
    private long sunset;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // Getters and setters
}
