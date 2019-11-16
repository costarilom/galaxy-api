package com.meli.galaxy.service;

import java.util.List;

import com.meli.galaxy.dto.WeatherDto;

public interface WeatherService {
	
	List<WeatherDto> getWeather();
	
	WeatherDto getWeatherByDay(String day);
}
