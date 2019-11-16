package com.meli.galaxy.schedulable.service;

import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meli.galaxy.service.CoordinateService;


@Component
public class ScheduledLicenceNotifications {
	
	@Resource
	CoordinateService coordinateService;

	@Scheduled(cron="${cron.expression.coordinates.all}")
	//Realiza la generacion de coordenadas para los proximos 10 años
	public void notifyLicenses() {
		System.out.println("Inicia la generacion de coordenadas para los planes Ferengi, Betasoide y Vulcano");
		coordinateService.generateCoordinatesAll();
		
	}
	
}
