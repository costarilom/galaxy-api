package com.meli.galaxy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Planet;
import com.meli.galaxy.repository.PlanetRepository;

@Service
public class PlanetServiceImp implements PlanetService {

	@Resource
	PlanetRepository planetRepository;
	
	@Override
	public Planet getPlanetByCode(String code) {
		return planetRepository.findPlanetByCode(code);
	}

	@Override
	public List<Planet> getAllPlanet() {
		return planetRepository.findAllPlanet();
	}

	@Override
	public boolean alignedPlanets(Coordinate planetA, Coordinate planetB, Coordinate planetC) {
		Integer A = Integer.valueOf(planetB.getLongitude()) - Integer.valueOf(planetA.getLongitude());
		Integer B = Integer.valueOf(planetB.getLatitude()) - Integer.valueOf(planetA.getLatitude());
		
		Integer C = Integer.valueOf(planetC.getLongitude()) - Integer.valueOf(planetA.getLongitude());
		Integer D = Integer.valueOf(planetC.getLatitude()) - Integer.valueOf(planetA.getLatitude());
		
		Integer aligned = (A * B) - (B * C);
		
		//Si la ecuacion da cero los planetas estan alineados, sino es un triangulo
		boolean result = aligned == 0 ? true: false;
		
		return result;
	}

	//Para llegar al perimetro maximo los 3 lados tiene que ser iguales
	@Override
	public boolean maximumPerimeter(Coordinate planetA, Coordinate planetB, Coordinate planetC) {
		boolean result = false;
		
		//Distancia entre A y B
		float distanceAB = calculateDistance(planetA, planetB);
		//Distancia entra A y C
		float distanceAC = calculateDistance(planetA, planetC);
		//Distancia entre B y C
		float distanceBC = calculateDistance(planetB, planetC);
		
		if(distanceAB == distanceAC && distanceAB == distanceBC && distanceAC == distanceBC){
			result = true;
		}
		
		return result;
	}

	private float calculateDistance(Coordinate ponitA, Coordinate pointB){
		//Resto los puntos X y los elevo al cuadrado
		double X = Math.pow(Integer.valueOf(pointB.getLongitude()) - Integer.valueOf(ponitA.getLongitude()), 2);
		//Resto los puntos Y y los elevo al cuadrado
		double Y = Math.pow(Integer.valueOf(pointB.getLatitude()) - Integer.valueOf(ponitA.getLatitude()), 2);
		
		//Calculo la raiz cuadrada de la suma de los mismos
		float result = (float) Math.sqrt(X + Y);
		
		return result;
	}

	/*
	 * Si los 3 resultados son del mismo signo el sol
	 * se encuentra dentro del triangulo. 
	 * Sino, si los signos son distintos el sol esta
	 * por fuera
	 */
	@Override
	public boolean sunInsideTheTriangle(Coordinate planetA, Coordinate planetB, Coordinate planetC) {
		//Distancia A
		double distanceA = (Integer.valueOf(planetA.getLongitude()) - Integer.valueOf(Constantconfig.SOL_X)) 
							* (Integer.valueOf(planetB.getLatitude()) - Integer.valueOf(Constantconfig.SOL_Y)) - 
				            (Integer.valueOf(planetB.getLongitude()) - Integer.valueOf(Constantconfig.SOL_X)) 
				            * (Integer.valueOf(planetA.getLatitude()) - Integer.valueOf(Constantconfig.SOL_Y));
		
		
		//Distancia B
		double distanceB = (Integer.valueOf(planetB.getLongitude()) - Integer.valueOf(Constantconfig.SOL_X)) 
							* (Integer.valueOf(planetC.getLatitude()) - Integer.valueOf(Constantconfig.SOL_Y)) - 
				            (Integer.valueOf(planetC.getLongitude()) - Integer.valueOf(Constantconfig.SOL_X)) 
				            * (Integer.valueOf(planetB.getLatitude()) - Integer.valueOf(Constantconfig.SOL_Y));
		
		//Distancia C
		double distanceC = (Integer.valueOf(planetB.getLongitude()) - Integer.valueOf(Constantconfig.SOL_X)) 
							* (Integer.valueOf(planetC.getLatitude()) - Integer.valueOf(Constantconfig.SOL_Y)) - 
				            (Integer.valueOf(planetC.getLongitude()) - Integer.valueOf(Constantconfig.SOL_X)) 
				            * (Integer.valueOf(planetB.getLatitude()) - Integer.valueOf(Constantconfig.SOL_Y));
		
		boolean result = false;
		if (distanceA < 0 && distanceB < 0 && distanceC < 0) {
			result = true;
		} else if (distanceA > 0 && distanceB > 0 && distanceC > 0) {
			result = true;
		}
		
		return result;
	}
}
