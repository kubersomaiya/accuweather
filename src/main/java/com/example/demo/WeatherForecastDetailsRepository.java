package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface WeatherForecastDetailsRepository extends JpaRepository<WeatherForecastDetails, Long> {

    
    @Query("SELECT a FROM WeatherForecastDetails a WHERE a.state = ?1 AND a.district = ?2 AND a.date >= CURRENT_DATE")
    List<WeatherForecastDetails> filterActualForecastData(String state, String district);

    @Query("SELECT a FROM WeatherForecastDetails a WHERE a.district = ?1 AND a.state = ?2 AND a.accuLocationKey = ?3 AND a.date = ?4")
    Optional<WeatherForecastDetails> findByDistrictAndStateAndAccuLocationKeyAndDate(String district, String state, String AccuLocationKey ,LocalDate date);
    
    @Modifying
    @Query("DELETE FROM WeatherForecastDetails a WHERE a.date >= CURRENT_DATE")
    void deleteForecastDataGreaterThanToday();
}
