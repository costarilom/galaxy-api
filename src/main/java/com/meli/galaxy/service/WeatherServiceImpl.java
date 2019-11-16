package com.meli.galaxy.service;

import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Override
	public String getWeather() {
		
		return null;
	}

	//Sequia?
	@Override
	public boolean droughtPeriod() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Lluvia?
	@Override
	public boolean rainyPeriod() {
		// TODO Auto-generated method stub
		return false;
	}

	//Pico de intensidad
	@Override
	public boolean intensityPeak() {
		// TODO Auto-generated method stub
		return false;
	}

	//Condiciones optimas
	@Override
	public boolean optimalConditions() {
		// TODO Auto-generated method stub
		return false;
	}


}
