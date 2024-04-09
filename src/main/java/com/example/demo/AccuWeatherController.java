package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccuWeatherController {

    @Autowired  private WeatherService weatherService;

    
    @GetMapping("/api/weather/get-forecast-data")
    public ResponseEntity<ResponseBaseDTO> getForecastDataFiltered(@RequestParam(required = false) String state,
                                                @RequestParam(required = false) String district) {
    ResponseServiceImpl obj = new ResponseServiceImpl();
    SuccessDTO successObj = new SuccessDTO();
    try {
        if (state != null && district != null) {
            successObj = obj.getSuccessResponse(HttpStatus.OK.value(), "success message", weatherService.getForecastDataByDistrict(state, district));
            
        } else {
            successObj = obj.getSuccessResponse(HttpStatus.OK.value(), "success message", weatherService.getAllForecastData());
        }
        return ResponseEntity.ok(successObj);
    } catch (Exception e) {
        ErrorDTO errorObj = obj.getErrorResponse(0, e.getMessage());
        return new ResponseEntity<>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
