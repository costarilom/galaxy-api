package com.meli.galaxy.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.dto.MeteorologicalConditionsDto;
import com.meli.galaxy.entity.MeteorologicalConditions;
import com.meli.galaxy.entity.Weather;
import com.meli.galaxy.repository.MeteorologicalConditionsRepository;

@Service
public class MeteorologicalConditionsServiceImpl implements MeteorologicalConditionsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
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
		
		logger.info("Se guardan las condiciones meteorologicas para le dia " + date);

		return save(meteorologicalConditions);
	}

	@Override
	public List<MeteorologicalConditionsDto> getPeriods() {
		return meteorologicalConditionsRepository.findPeriods();
	}

	@Override
	public List<MeteorologicalConditionsDto> getPeriodsByYear(String year) {
		return meteorologicalConditionsRepository.findPeriodsByYear(Integer.parseInt(year));
	}

	@Override
	public MeteorologicalConditionsDto getWeatherByDay(String daystr) {
		MeteorologicalConditionsDto meteorologicalConditionsDto = new MeteorologicalConditionsDto();
		//Calculo la fecha a buscar
		Date date = utilService.addDay(utilService.stringToDate(Constantconfig.dateFrom), daystr);
		MeteorologicalConditions meteorologicalConditions = meteorologicalConditionsRepository.findWeatherByDay(utilService.getDateFrom(date), utilService.getDateTo(date));
		if(meteorologicalConditions != null){
			meteorologicalConditionsDto.setDay(daystr);
			meteorologicalConditionsDto.setWeather(meteorologicalConditions.getWeather().getName());
		}else{
			logger.info("No se encontró condición climática para el día  " + date);

			new Exception("No se encontró condición climática para el día consultado");
		}
		
		return meteorologicalConditionsDto;
	}

	@Override
	public MeteorologicalConditionsDto getPeriodsByCode(String code) {
		MeteorologicalConditionsDto meteorologicalConditionsDto = new MeteorologicalConditionsDto();

		List<MeteorologicalConditions> meteorologicalConditionsList= meteorologicalConditionsRepository.findPeriodsByCode(code);
		
		if(meteorologicalConditionsList != null && !meteorologicalConditionsList.isEmpty()){
			meteorologicalConditionsDto.setDays(String.valueOf(meteorologicalConditionsList.size()));
			meteorologicalConditionsDto.setWeather(meteorologicalConditionsList.get(0).getWeather().getName());
		}else{
			logger.info("No se encontró condición climática para el clima consultado");

			new Exception("No se encontró condición climática para el clima consultado");
		}
		
		return meteorologicalConditionsDto;
	}


}
