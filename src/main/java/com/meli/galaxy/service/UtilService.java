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
	
	public double[] leftRotation(PlanetDto planetDto){
		double X = Double.valueOf(planetDto.getLatitude());
		double Y = Double.valueOf(planetDto.getLongitude());		
		double displacement = Math.toRadians(planetDto.getDisplacement());
		 		
		//Formula pa obtener X' 
		double XP = X * Math.cos(displacement) + Y * Math.sin(displacement);
		//Formula para obtener Y'
		double YP = Y * Math.cos(displacement) - X * Math.sin(displacement);
		
		double[] coordinate = {XP, YP};    

	    return coordinate;
	}
	
	public double[] rightRotation(PlanetDto planetDto){
		double X = Double.valueOf(planetDto.getLatitude());
		double Y = Double.valueOf(planetDto.getLongitude());
		double displacement = Math.toRadians(planetDto.getDisplacement());
		
		//Formula pa obtener X'
		double XP = X * Math.cos(displacement) - Y * Math.sin(displacement);
		//Formula para obtener Y'
		double YP = X * Math.sin(displacement) + Y * Math.cos(displacement);
				
		double[] coordinate = {XP, YP};    

	    return coordinate;
	}
}
