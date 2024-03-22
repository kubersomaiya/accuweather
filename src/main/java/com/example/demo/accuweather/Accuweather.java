package com.example.demo.accuweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;



@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class Accuweather {

    @Id
    @SequenceGenerator(name = "accuweather_sequence", sequenceName = "accuweather_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accuweather_sequence")
    private Long id;
    private String state;
    private String district;
    private String accuweather_locationKey;
    private String minTemp;
    private String maxTemp;
    private String dayForecast;
    private String nightForecast;

    public Accuweather() {
    }

    public Accuweather(Long id, String state, String district, String accuweather_locationKey , String minTemp , String maxTemp , String dayForecast , String nightForecast) {
        this.id = id;
        this.state = state;
        this.district = district;
        this.accuweather_locationKey = accuweather_locationKey;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.dayForecast = dayForecast;
        this.nightForecast = nightForecast;
    }


    public Accuweather(String state, String district, String accuweather_locationKey , String minTemp , String maxTemp , String dayForecast , String nightForecast) {
        this.state = state;
        this.district = district;
        this.accuweather_locationKey = accuweather_locationKey;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.dayForecast = dayForecast;
        this.nightForecast = nightForecast;
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getAccuweather_locationKey() {
        return accuweather_locationKey;
    }


    
    public String getMinTemp(){
        return minTemp;
    }

    public String getMaxTemp(){
        return maxTemp;
    }

    public String getDayForecast(){
        return dayForecast;
    }

    public String getNightForecast(){
        return nightForecast;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String setState() {
        return state;
    }

    public String setDistrict() {
        return district;
    }
    
    public String setAccuweather_locationKey(String locationKey) {
        // return accuweather_locationKey;
        this.accuweather_locationKey = locationKey;
        return accuweather_locationKey;
    }

    public String setMinTemp(){
        return minTemp;
    }

    public String setMaxTemp(){
        return maxTemp;
    }

    public String setDayForecast(){
        return dayForecast;
    }

    public String setNightForecast(){
        return nightForecast;
    }
}