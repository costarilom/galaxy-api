package com.meli.galaxy.service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Weather;
import com.meli.galaxy.repository.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Resource
	UtilService utilService;

	@Resource
	CoordinateService coordinateService;

	@Resource
	PlanetService planetService;
	
	@Resource
	WeatherRepository weatherRepository;
	
	@Resource
	MeteorologicalConditionsService meteorologicalConditionsService;


	@Override
	public void saveWeatherByDate(Date date) {
		//Busco las coordenadas para el dia
		List<Coordinate> coordinate = coordinateService.getCoordinatesByDate(date);
		if (coordinate != null && !coordinate.isEmpty() && coordinate.size() == 3) {
			Coordinate planetA = coordinate.get(0);
			Coordinate planetB = coordinate.get(1);
			Coordinate planetC = coordinate.get(2);
			// Pregunto si los 3 planetas estan alineados
			boolean aligned = planetService.alignedPlanets(planetA, planetB, planetC);

			// si estan alineados consulto si tambien lo estan con el sol
			if (aligned) {
				boolean alignedWithTheSun = planetService.planetsAlignedWithTheSun(planetA, planetB);
				if (alignedWithTheSun) {
					saveMeteorologicalCondition(date, Constantconfig.DROUGHT_CODE);
				} else {
					saveMeteorologicalCondition(date, Constantconfig.OPTIMALCONDITIONST_CODE);
				}
			} else {
				// Como es un triangulo consulto si el sol esta dentro del mismo
				boolean sunInsideTheTriangle = planetService.sunInsideTheTriangle(planetA, planetB, planetC);
				// Si el sol esta dentro, consulto si es el perimetro maximo
				if (sunInsideTheTriangle) {
					boolean maximumPerimeter = planetService.maximumPerimeter(planetA, planetB, planetC);
					if (maximumPerimeter) {
						saveMeteorologicalCondition(date, Constantconfig.INTENSITYPEAKT_CODE);
					} else {
						saveMeteorologicalCondition(date, Constantconfig.RAINY_CODE);
					}
				}
			}
		}
	}
	
	private void saveMeteorologicalCondition(Date date, String code){
		Weather weather = getWeatherByCode(code);
		meteorologicalConditionsService.createMeteorologicalConditions(date, weather);
	}

	@Override
	public Weather getWeatherByCode(String code) {
		return weatherRepository.findWeatherByCode(code);
	}
}
