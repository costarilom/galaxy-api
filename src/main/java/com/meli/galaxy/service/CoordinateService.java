package com.meli.galaxy.service;

import java.util.List;

import com.meli.galaxy.entity.Coordinate;

public interface CoordinateService {
	
	public Coordinate save(Coordinate coordinate);

	public void generateCoordinatesAll();
	
	public List<Coordinate> getCoordinateByPlanetId(Integer planetId);
}
