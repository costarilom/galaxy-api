package com.meli.galaxy.service;

import java.util.Date;
import java.util.List;

import com.meli.galaxy.dto.WeatherDto;
import com.meli.galaxy.entity.Weather;

public interface WeatherService {
	
	List<WeatherDto> getWeather();
	
	Weather getWeatherByCode(String code);
	
	WeatherDto getWeatherByDay(String day);
	
	void saveWeatherByDate(Date date);
}
