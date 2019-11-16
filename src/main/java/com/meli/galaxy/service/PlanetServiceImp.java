package com.meli.galaxy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Planet;
import com.meli.galaxy.repository.PlanetRepository;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

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
		double A = Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(planetA.getLongitude());
		double B = Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(planetA.getLatitude());
		
		double C = Double.parseDouble(planetC.getLongitude()) - Double.parseDouble(planetA.getLongitude());
		double D = Double.parseDouble(planetC.getLatitude()) - Double.parseDouble(planetA.getLatitude());
		
		double aligned = (A * D) - (B * C);
		
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
		double X = Math.pow(Double.parseDouble(pointB.getLongitude()) - Double.parseDouble(ponitA.getLongitude()), 2);
		//Resto los puntos Y y los elevo al cuadrado
		double Y = Math.pow(Double.parseDouble(pointB.getLatitude()) - Double.parseDouble(ponitA.getLatitude()), 2);
		
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
		double distanceA = (Double.parseDouble(planetA.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X)) 
							* (Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y)) - 
				            (Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X)) 
				            * (Double.parseDouble(planetA.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y));
		
		
		//Distancia B
		double distanceB = (Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X)) 
							* (Double.parseDouble(planetC.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y)) - 
				            (Double.parseDouble(planetC.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X)) 
				            * (Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y));
		
		//Distancia C
		double distanceC = (Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X)) 
							* (Double.parseDouble(planetC.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y)) - 
				            (Double.parseDouble(planetC.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X)) 
				            * (Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y));
		
		boolean result = false;
		if (distanceA < 0 && distanceB < 0 && distanceC < 0) {
			result = true;
		} else if (distanceA > 0 && distanceB > 0 && distanceC > 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean planetsAlignedWithTheSun(Coordinate planetA, Coordinate planetB) {
		//Verifico que el sol este alineado con los planetas 
		double A = Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(planetA.getLongitude());
		double B = Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(planetA.getLatitude());
		
		double C = Double.parseDouble(Constantconfig.SOL_X) - Double.parseDouble(planetA.getLongitude());
		double D = Double.parseDouble(Constantconfig.SOL_Y) - Double.parseDouble(planetA.getLatitude());
		
		double aligned = (A * D) - (B * C);
		
		//Si la ecuacion da cero los planetas estan alineados, sino es un triangulo
		boolean result = aligned == 0 ? true: false;
		
		return result;
	}
}
