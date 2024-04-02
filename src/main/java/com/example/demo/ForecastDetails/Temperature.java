package com.example.demo.ForecastDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class Temperature {
    @JsonProperty("Minimum")
    public Minimum Minimum;
    @JsonProperty("Maximum")
    public Maximum Maximum;

    // Getters and setters
    public Minimum getMinimum() {
        return Minimum;
    }

    public void setMinimum(Minimum minimum) {
        Minimum = minimum;
    }

    public Maximum getMaximum() {
        return Maximum;
    }

    public void setMaximum(Maximum maximum) {
        Maximum = maximum;
    }
}
