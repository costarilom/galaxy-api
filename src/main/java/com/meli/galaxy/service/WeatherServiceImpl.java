package com.meli.galaxy.service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Weather;
import com.meli.galaxy.repository.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		logger.info("Se buscan las coordenadas para el dia : " + date);

		if (coordinate != null && !coordinate.isEmpty() && coordinate.size() == 3) {
			//Si bien se obtiene una lista de coordenadas, solo nos interesan las ultimas
			Coordinate planetA = coordinate.get(0);
			Coordinate planetB = coordinate.get(1);
			Coordinate planetC = coordinate.get(2);
			// Pregunto si los 3 planetas estan alineados
			logger.info("Pregunto si los 3 planetas estan alineados");
			boolean aligned = planetService.alignedPlanets(planetA, planetB, planetC);

			// si estan alineados consulto si tambien lo estan con el sol
			if (aligned) {
				logger.info("Si estan alineados consulto si tambien lo estan con el sol");
				boolean alignedWithTheSun = planetService.planetsAlignedWithTheSun(planetA, planetB);
				if (alignedWithTheSun) {
					logger.info("Estan alineados con el sol");
					saveMeteorologicalCondition(date, Constantconfig.DROUGHT_CODE);
				} else {
					logger.info("Estan alineados sin el sol");
					saveMeteorologicalCondition(date, Constantconfig.OPTIMALCONDITIONST_CODE);
				}
			} else {
				logger.info("Como es un triangulo consulto si el sol esta dentro del mismo");
				// Como es un triangulo consulto si el sol esta dentro del mismo
				boolean sunInsideTheTriangle = planetService.sunInsideTheTriangle(planetA, planetB, planetC);
				// Si el sol esta dentro, consulto si es el perimetro maximo
				if (sunInsideTheTriangle) {
					logger.info("Si el sol esta dentro, consulto si es el perimetro maximo");
					boolean maximumPerimeter = planetService.maximumPerimeter(planetA, planetB, planetC);
					if (maximumPerimeter) {
						logger.info("El triangulo esta en el perimetro maximo");
						saveMeteorologicalCondition(date, Constantconfig.INTENSITYPEAKT_CODE);
					} else {
						logger.info("El triangulo NO esta en el perimetro maximo");
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

	@Override
	public void initialize() {
		Weather weather1 = new Weather();
		weather1.setCode("DRO");
		weather1.setName("Sequía");
		weatherRepository.save(weather1);
		logger.info("Se guardo clima 1");
		
		Weather weather2 = new Weather();
		weather2.setCode("RAI");
		weather2.setName("Lluvia");
		weatherRepository.save(weather2);
		logger.info("Se guardo clima 2");
		
		Weather weather3 = new Weather();
		weather3.setCode("INT");
		weather3.setName("Pico de intensidad");
		weatherRepository.save(weather3);
		logger.info("Se guardo clima 3");
		
		Weather weather4 = new Weather();
		weather4.setCode("OPT");
		weather4.setName("Condiciones optimas de presión y temperatura");
		weatherRepository.save(weather4);
		logger.info("Se guardo clima 4");
	}
}
