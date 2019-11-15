package com.meli.galaxy.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.meli.galaxy.dto.PlanetDto;

@Service
public class UtilService {

	public Integer gerCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	public Integer getYearByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
	
		return year;
	}
	
	public Double[] rightRotation(PlanetDto planetDto){
		Double X = Double.valueOf(planetDto.getLongitude());
		Double Y = Double.valueOf(planetDto.getLatitude());
		Integer displacement = planetDto.getDisplacement();
		
		//Formula pa obtener X'
		Double XP = X * Math.cos(displacement) + Y * Math.sin(displacement);
		Double YP = Y * Math.cos(displacement) - X * Math.sin(displacement);
		
		//Formula para obtener Y'
		
		Double[] coordinate = {XP, YP};    

	    return coordinate;
	}
	
	public Double[] leftRotation(PlanetDto planetDto){
		Double X = Double.valueOf(planetDto.getLongitude());
		Double Y = Double.valueOf(planetDto.getLatitude());
		Integer displacement = planetDto.getDisplacement();
		
		//Formula pa obtener X'
		Double XP = X * Math.cos(displacement) - Y * Math.sin(displacement);
		Double YP = X * Math.sin(displacement) + Y * Math.cos(displacement);
		
		//Formula para obtener Y'
		
		Double[] coordinate = {XP, YP};    

	    return coordinate;
	}
}
