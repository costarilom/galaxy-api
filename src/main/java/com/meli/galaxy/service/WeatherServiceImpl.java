package com.meli.galaxy.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.dto.WeatherDto;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Migrate;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Resource
	UtilService utilService;
	
	@Resource
	CoordinateService coordinateService;
	
	@Resource
	PlanetService planetService;
	
	@Override
	public List<WeatherDto> getWeather() {
		//sequia
		int drought = 0;
		//Lluvia 
		int rainy = 0;
		//Pico de intensidad
		int intensityPeak = 0;
		//Condiciones optimas 
		int optimalConditions = 0;
		
		//Itero por cada dia consultando el clima
		Date dateFrom = utilService.stringToDate(Constantconfig.dateFrom);
		
		System.out.println("El dia actual es " + dateFrom + "\n");
				
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFrom);
		calendar.add(Calendar.YEAR, Constantconfig.YEAR_MAX);

		Date dateTo = calendar.getTime();

		// Comienzo la iteracion por dia
		while (dateFrom.getTime() < dateTo.getTime()) {
			// controlo de no haber llegado a la fecha de fin
			if (!dateFrom.after(dateTo)) {
				List<Coordinate> coordinate = coordinateService.getCoordinatesByDate(dateFrom);
				if(coordinate != null && !coordinate.isEmpty() && coordinate.size() == 3){
					//Pregunto si los 3 planetas estan alineados
					boolean aligned = planetService.alignedPlanets(coordinate.get(0), coordinate.get(1), coordinate.get(2));
					
					//si estan alineados consulto si tambien lo estan con el sol
					if(aligned){
						boolean alignedWithTheSun = planetService.planetsAlignedWithTheSun(coordinate.get(0), coordinate.get(1));
						if(alignedWithTheSun){
							drought++;
						}else{
							optimalConditions++;
						}
					}
					else{
						//Como es un triangulo consulto si el sol esta dentro del mismo
						boolean sunInsideTheTriangle = planetService.sunInsideTheTriangle(coordinate.get(0), coordinate.get(1), coordinate.get(2));
						//Si el sol esta dentro, consulto si es el perimetro maximo
						if(sunInsideTheTriangle){
							boolean maximumPerimeter = planetService.maximumPerimeter(coordinate.get(0), coordinate.get(1), coordinate.get(2));
							if(maximumPerimeter){
								intensityPeak++;
							}else{
								rainy++;
							}
						}
					}
					
				}
				// Incrementas un dia (86400000 es un dia en milisegundos)
				dateFrom.setTime((long) dateFrom.getTime() + (86400000));
			}
		}
		
		return createResponse(drought, rainy, intensityPeak, optimalConditions);
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

	private List<WeatherDto> createResponse(int drought, int rainy, int intensityPeak, int optimalConditions){
		List<WeatherDto> weathersDto = new ArrayList<>();
		
		WeatherDto weatherDtoDrought = new WeatherDto();
		weatherDtoDrought.setWeather(Constantconfig.DROUGHT);
		weatherDtoDrought.setDays(Integer.toString(drought));
		weathersDto.add(weatherDtoDrought);
		
		WeatherDto weatherDtoDroughtRainy = new WeatherDto();
		weatherDtoDroughtRainy.setWeather(Constantconfig.RAINY);
		weatherDtoDroughtRainy.setDays(Integer.toString(rainy));
		weathersDto.add(weatherDtoDroughtRainy);
		
		WeatherDto weatherDtoDroughtIntensityPeak = new WeatherDto();
		weatherDtoDroughtIntensityPeak.setWeather(Constantconfig.INTENSITYPEAK);
		weatherDtoDroughtIntensityPeak.setDays(Integer.toString(intensityPeak));
		weathersDto.add(weatherDtoDroughtIntensityPeak);
		
		WeatherDto weatherDtoDroughtOptimalConditions = new WeatherDto();
		weatherDtoDroughtOptimalConditions.setWeather(Constantconfig.OPTIMALCONDITIONS);
		weatherDtoDroughtOptimalConditions.setDays(Integer.toString(optimalConditions));
		weathersDto.add(weatherDtoDroughtOptimalConditions);
		
		return weathersDto;
	}
}
