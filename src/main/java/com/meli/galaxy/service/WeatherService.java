package com.meli.galaxy.service;

import java.util.List;

import com.meli.galaxy.dto.WeatherDto;

public interface WeatherService {
	
	List<WeatherDto> getWeather();
	
	List<WeatherDto> getWeatherByDay(String day);
	
	//Sequia?
	boolean droughtPeriod();
	
	//Lluvia?
	boolean rainyPeriod();
	
	//Pico de intensidad
	boolean intensityPeak();
	
	//Condiciones optimas
	boolean optimalConditions();
}
