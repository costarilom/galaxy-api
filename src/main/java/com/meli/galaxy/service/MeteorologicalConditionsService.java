package com.meli.galaxy.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.meli.galaxy.dto.MeteorologicalConditionsDto;
import com.meli.galaxy.entity.MeteorologicalConditions;
import com.meli.galaxy.entity.Weather;

public interface MeteorologicalConditionsService {
	
	MeteorologicalConditions createMeteorologicalConditions(Date date, Weather weather);
	
	MeteorologicalConditions save(MeteorologicalConditions meteorologicalConditions);
	
	List<MeteorologicalConditionsDto> getPeriods();
	
	MeteorologicalConditionsDto getPeriodsByCode(String code);
	
	List<MeteorologicalConditionsDto> getPeriodsByYear(String year);
	
	MeteorologicalConditionsDto getWeatherByDay(String day);
	
	Map<String, String> clean();
	
	Map<String, String> initialize();
}
