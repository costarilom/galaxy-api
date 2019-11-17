package com.meli.galaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.meli.galaxy.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	
	@Query("SELECT w FROM Weather w WHERE w.code = :code ")
	Weather findWeatherByCode(@Param("code") String code);

}
