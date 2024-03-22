package com.example.demo.accuweather;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccuWeatherRepository extends JpaRepository<Accuweather, Long> {

    // @Query("SELECT a.district FROM Accuweather a")
    // List<String> findDistricts();
}
