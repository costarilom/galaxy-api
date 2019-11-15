package com.meli.galaxy.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.meli.galaxy.config.Constantconfig;

@Service
public class CoordinateServiceImpl implements CoordinateService {

	@Override
	public void generateCoordinatesAll() {
		//Obtengo el dia actual para sumarle los 10 años
		Date dateFrom = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFrom);
		calendar.add(Calendar.YEAR, Constantconfig.YEAR_MAX);
		
		Date dateTo = calendar.getTime();
		
		//Insertar en coodinate las coordenadas iniciales de cada planeta
		//TODO
		
		//Comienzo la iteracion por dia
		while (dateFrom.getTime() < dateTo.getTime() ){
			//controlo de no haber llegado a la fecha de fin
			if(!dateFrom.after(dateTo)){
				// Incrementas un dia (86400000 es un dia en milisegundos)
				dateFrom.setTime((long) dateFrom.getTime()+(86400000));
				
				//Llamar al meto de rotacion de planetas
				
				//Hacer el insert en la tabla de coordenadas
				
				System.out.print("Se procesa el dia " + dateFrom + "\n");

			}
		}
	}

	private rotatePlanet(){
		
	}
}
