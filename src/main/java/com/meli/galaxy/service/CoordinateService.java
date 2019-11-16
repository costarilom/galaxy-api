package com.meli.galaxy.service;

import java.util.Date;
import java.util.List;

import com.meli.galaxy.entity.Coordinate;

public interface CoordinateService {
	
	Coordinate save(Coordinate coordinate);

	void generateCoordinatesAll();
	
	List<Coordinate> getCoordinateByPlanetId(Integer planetId);
	
	List<Coordinate> getCoordinatesByDate(Date date);
}
