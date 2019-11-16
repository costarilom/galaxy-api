package com.meli.galaxy.service;

import com.meli.galaxy.dto.WeatherDto;

public interface WeatherService {
	
	WeatherDto getWeather();
	
	//Sequia?
	boolean droughtPeriod();
	
	//Lluvia?
	boolean rainyPeriod();
	
	//Pico de intensidad
	boolean intensityPeak();
	
	//Condiciones optimas
	boolean optimalConditions();
}
