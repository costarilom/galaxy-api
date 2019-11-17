package com.meli.galaxy.service;


import java.util.Date;

import com.meli.galaxy.entity.MeteorologicalConditions;
import com.meli.galaxy.entity.Weather;

public interface MeteorologicalConditionsService {
	
	MeteorologicalConditions createMeteorologicalConditions(Date date, Weather weather);
	
	MeteorologicalConditions save(MeteorologicalConditions meteorologicalConditions);
}
