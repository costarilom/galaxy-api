package com.meli.galaxy.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.meli.galaxy.entity.MeteorologicalConditions;
import com.meli.galaxy.entity.Weather;
import com.meli.galaxy.repository.MeteorologicalConditionsRepository;

@Service
public class MeteorologicalConditionsServiceImpl implements MeteorologicalConditionsService {

	@Resource
	MeteorologicalConditionsRepository meteorologicalConditionsRepository;
	
	@Resource
	UtilService utilService;
	
	@Override
	public MeteorologicalConditions save(MeteorologicalConditions meteorologicalConditions) {
		return meteorologicalConditionsRepository.save(meteorologicalConditions);
	}

	@Override
	public MeteorologicalConditions createMeteorologicalConditions(Date date, Weather weather) {
		MeteorologicalConditions meteorologicalConditions = new MeteorologicalConditions();
		meteorologicalConditions.setDate(date);
		meteorologicalConditions.setYear(utilService.getYearByDate(date));
		meteorologicalConditions.setWeather(weather);
		
		return save(meteorologicalConditions);
	}


}
