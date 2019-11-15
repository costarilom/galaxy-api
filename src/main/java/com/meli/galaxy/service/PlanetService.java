package com.meli.galaxy.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.meli.galaxy.entity.Planet;

public interface PlanetService {

	Planet getPlanetByCode(@Param("code") String code);
	
	List<Planet> getAllPlanet();
}
