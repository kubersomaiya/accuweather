package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "location_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDetails {

    @Id
    @SequenceGenerator(name = "accuweather_sequence", sequenceName = "accuweather_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accuweather_sequence")
    private Long id;

    private String state;

    private String district;

    private String accuweather_locationKey;

    public LocationDetails() {
    }

    public LocationDetails(String state, String district, String accuweather_locationKey) {
        this.state = state;
        this.district = district;
        this.accuweather_locationKey = accuweather_locationKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAccuweather_locationKey() {
        return accuweather_locationKey;
    }

    public void setAccuweather_locationKey(String accuweather_locationKey) {
        this.accuweather_locationKey = accuweather_locationKey;
    }
}
