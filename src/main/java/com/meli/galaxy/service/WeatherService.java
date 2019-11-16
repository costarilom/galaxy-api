package com.meli.galaxy.service;

public interface WeatherService {
	
	String getWeather();
	
	//Sequia?
	boolean droughtPeriod();
	
	//Lluvia?
	boolean rainyPeriod();
	
	//Pico de intensidad
	boolean intensityPeak();
	
	//Condiciones optimas
	boolean optimalConditions();
}
