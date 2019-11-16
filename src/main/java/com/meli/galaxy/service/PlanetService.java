package com.meli.galaxy.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Planet;

public interface PlanetService {

	Planet getPlanetByCode(@Param("code") String code);
	
	List<Planet> getAllPlanet();
	
	boolean alignedPlanets(Coordinate planetA, Coordinate planetB, Coordinate planetC);
	
	boolean planetsAlignedWithTheSun(Coordinate planetA, Coordinate planetB);
	
	boolean maximumPerimeter(Coordinate planetA, Coordinate planetB, Coordinate planetC);
	
	boolean sunInsideTheTriangle(Coordinate planetA, Coordinate planetB, Coordinate planetC);
}
