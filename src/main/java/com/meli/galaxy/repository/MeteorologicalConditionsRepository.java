package com.meli.galaxy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meli.galaxy.dto.MeteorologicalConditionsDto;
import com.meli.galaxy.entity.MeteorologicalConditions;

@Repository
public interface MeteorologicalConditionsRepository extends JpaRepository<MeteorologicalConditions, Integer>{

	
	@Query("SELECT new com.meli.galaxy.dto.MeteorologicalConditionsDto(w.name, COUNT(mc.id))"
			+ "FROM MeteorologicalConditions mc"
			+ "INNER JOIN mc.weather w "
			+ "GROUP BY w.name"
			+ "ORDER BY w.name")
	List<MeteorologicalConditionsDto> findPeriods();
	
	@Query("SELECT mc "
			+ "FROM MeteorologicalConditions mc"
			+ "INNER JOIN mc.weather w "
			+ "WHERE mc.date BETWEEN :dateFrom AND :dateTo")
	MeteorologicalConditions findWeatherByDay(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
	
	
	@Query("SELECT new com.meli.galaxy.dto.MeteorologicalConditionsDto(w.name, COUNT(mc.id))"
			+ "FROM MeteorologicalConditions mc"
			+ "INNER JOIN mc.weather w "
			+ "WHERE mc.year = :year "
			+ "GROUP BY w.name"
			+ "ORDER BY w.name")
	List<MeteorologicalConditionsDto> findPeriodsByYear(@Param("year") Integer year);
	
	@Query("SELECT DISTINCT mc"
			+ "FROM MeteorologicalConditions mc"
			+ "INNER JOIN mc.weather w "
			+ "WHERE w.code = :code ")
	List<MeteorologicalConditionsDto> findPeriodsByCode(@Param("code") String code);
}
