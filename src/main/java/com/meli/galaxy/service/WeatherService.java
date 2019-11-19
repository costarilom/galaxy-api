package com.meli.galaxy.service;

import java.util.Date;
import com.meli.galaxy.entity.Weather;

public interface WeatherService {
		
	Weather getWeatherByCode(String code);
	
	void saveWeatherByDate(Date date);
	
	void initialize();

}
