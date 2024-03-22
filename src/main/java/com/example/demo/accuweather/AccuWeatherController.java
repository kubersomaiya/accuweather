package com.example.demo.accuweather;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "api/v1/accuweather")
public class AccuWeatherController {

    private final AccuWeatherService accuWeatherService;

    @Autowired
    private AccuWeatherController(AccuWeatherService accuWeatherService) {
        this.accuWeatherService = accuWeatherService;
    }

    @GetMapping
    public List<Accuweather> getDisticts() {
        return accuWeatherService.getDisticts();
    }
    
    // @GetMapping()
    // public Long getLocationKey() throws IOException{
    //     return accuWeatherService.getLocationKey("Mumbai");
    // }

    @PostMapping
    public void registerNewDistrict(@RequestBody Accuweather accuWeatherObj){
        accuWeatherService.addNewDistrict(accuWeatherObj);
    }
}
