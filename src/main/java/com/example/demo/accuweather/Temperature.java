package com.example.demo.accuweather;

// Temperature.java
public class Temperature {
    private TemperatureValue minimum;
    private TemperatureValue maximum;

    public TemperatureValue getMinimum() {
        return minimum;
    }

    public void setMinimum(TemperatureValue minimum) {
        this.minimum = minimum;
    }

    public TemperatureValue getMaximum() {
        return maximum;
    }

    public void setMaximum(TemperatureValue maximum) {
        this.maximum = maximum;
    }
}
