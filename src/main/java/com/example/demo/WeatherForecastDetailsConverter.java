package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WeatherForecastDetailsConverter implements Converter<List<WeatherForecastDetails>, List<WeatherForecastDetailsDTO>> {

    
    @Override
    public List<WeatherForecastDetailsDTO> convert(List<WeatherForecastDetails> source) {
        return source.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    private WeatherForecastDetailsDTO entityToDto(WeatherForecastDetails weatherForecastDetails) {
        WeatherForecastDetailsDTO dto = new WeatherForecastDetailsDTO();
        dto.setId(weatherForecastDetails.getId());
        dto.setDate(weatherForecastDetails.getDate());
        dto.setAccuLocationKey(weatherForecastDetails.getAccuLocationKey());
        dto.setDistrict(weatherForecastDetails.getDistrict());
        dto.setState(weatherForecastDetails.getState());
        dto.setMinTemp(weatherForecastDetails.getMinTemp());
        dto.setMaxTemp(weatherForecastDetails.getMaxTemp());
        dto.setDayForecast(weatherForecastDetails.getDayForecast());
        dto.setNightForecast(weatherForecastDetails.getNightForecast());

        return dto;
    }
}
