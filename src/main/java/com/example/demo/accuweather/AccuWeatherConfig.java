package com.example.demo.accuweather;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class AccuWeatherConfig {
    
    private final AccuWeatherService accuWeatherService;

    @Autowired
    public AccuWeatherConfig(AccuWeatherService accuWeatherService) {
        this.accuWeatherService = accuWeatherService;
    }

    @Bean
    CommandLineRunner commandLineRunner(AccuWeatherRepository repository){
        return args ->{
            Accuweather nobj1 = new Accuweather(
                "Maharashtra",
                "Mumbai", 
                "",
                "",
                "", 
                "",
                ""
            );
            Accuweather nobj2 = new Accuweather(
                "Maharashtra",
                "Thane", 
                "",
                "",
                "", 
                "",
                ""
            );
            Accuweather nobj3 = new Accuweather(
                "Maharashtra",
                "Nagpur", 
                "",
                "",
                "", 
                "",
                ""
            );
            Accuweather nobj4 = new Accuweather(
                "Maharashtra",
                "Pune", 
                "",
                "",
                "", 
                "",
                ""
            );
            List<Accuweather> dummyData = List.of(nobj1,nobj2,nobj3,nobj4);
            // repository.saveAll(List.of(obj1,obj2,obj3));
            repository.saveAll(dummyData);
            List<Accuweather> updatedDb = accuWeatherService.callLocationApiForAllData();
            repository.saveAll(updatedDb);
            // accuWeatherService.getForecastData("204847");
        };  
    }
}
