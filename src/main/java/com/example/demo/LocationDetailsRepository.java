package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDetailsRepository extends JpaRepository<LocationDetails, Long> {

    @Query("SELECT ld FROM LocationDetails ld WHERE ld.accuweather_locationKey = ''")
    List<LocationDetails> findDistrictsWithLocationKeyNull();

    @Query("SELECT ld.state FROM LocationDetails ld WHERE ld.district = ?1")
    String findStateWithDistrict(String district);

    @Query("SELECT ld.district FROM LocationDetails ld")
    List<String> getAllDistricts();
}
