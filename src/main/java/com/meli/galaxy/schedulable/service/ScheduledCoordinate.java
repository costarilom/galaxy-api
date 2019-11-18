package com.meli.galaxy.schedulable.service;

import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.meli.galaxy.service.CoordinateService;


@Component
public class ScheduledCoordinate {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Resource
	CoordinateService coordinateService;

	@Scheduled(cron="${cron.expression.coordinates.all}")
	//Realiza la generacion de coordenadas para los proximos 10 años
	public void coordinates() {
		logger.info("Inicia la generacion de coordenadas para los planes Ferengi, Betasoide y Vulcano");
		coordinateService.generateCoordinatesAll(); 
	}
	
}
