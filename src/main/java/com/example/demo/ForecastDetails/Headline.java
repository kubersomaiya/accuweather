package com.example.demo.ForecastDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Headline {
    @JsonProperty("EffectiveDate")
    private String effectiveDate;
    @JsonProperty("Text")
    private String Text;

    // Getters and setters
    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}