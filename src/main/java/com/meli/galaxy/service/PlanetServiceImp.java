package com.meli.galaxy.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Planet;
import com.meli.galaxy.repository.PlanetRepository;

@Service
public class PlanetServiceImp implements PlanetService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		boolean result = false;
		try {
			double A = Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(planetA.getLongitude());
			double B = Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(planetA.getLatitude());

			double C = Double.parseDouble(planetC.getLongitude()) - Double.parseDouble(planetA.getLongitude());
			double D = Double.parseDouble(planetC.getLatitude()) - Double.parseDouble(planetA.getLatitude());

			double aligned = (A * D) - (B * C);

			// Si la ecuacion da cero los planetas estan alineados, sino es un
			// triangulo
			result = aligned == 0 ? true : false;

			logger.info("Los planetas no se encuentan alineados, result: " + result);
		} catch (Exception e) {
			logger.info("Ocurrio un error en el metodo alignedPlanets: " + e.toString());
			e.printStackTrace();
		}
		return result;
	}

	// Para llegar al perimetro maximo los 3 lados tiene que ser iguales
	@Override
	public boolean maximumPerimeter(Coordinate planetA, Coordinate planetB, Coordinate planetC) {
		boolean result = false;
		try {
			// Distancia entre A y B
			float distanceAB = calculateDistance(planetA, planetB);
			// Distancia entra A y C
			float distanceAC = calculateDistance(planetA, planetC);
			// Distancia entre B y C
			float distanceBC = calculateDistance(planetB, planetC);

			if (distanceAB == distanceAC && distanceAB == distanceBC && distanceAC == distanceBC) {
				result = true;
			}
		} catch (Exception e) {
			logger.info("Ocurrio un error en el metodo maximumPerimeter: " + e.toString());
			e.printStackTrace();
		}

		return result;
	}

	private float calculateDistance(Coordinate ponitA, Coordinate pointB) {
		float result = 0;
		try {
			// Resto los puntos X y los elevo al cuadrado
			double X = Math.pow(Double.parseDouble(pointB.getLongitude()) - Double.parseDouble(ponitA.getLongitude()),
					2);
			// Resto los puntos Y y los elevo al cuadrado
			double Y = Math.pow(Double.parseDouble(pointB.getLatitude()) - Double.parseDouble(ponitA.getLatitude()), 2);

			// Calculo la raiz cuadrada de la suma de los mismos
			result = (float) Math.sqrt(X + Y);
		} catch (Exception e) {
			logger.info("Ocurrio un error en el metodo calculateDistance: " + e.toString());
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * Si los 3 resultados son del mismo signo el sol se encuentra dentro del
	 * triangulo. Sino, si los signos son distintos el sol esta por fuera
	 */
	@Override
	public boolean sunInsideTheTriangle(Coordinate planetA, Coordinate planetB, Coordinate planetC) {
		boolean result = false;
		try {
			// Distancia A
			double distanceA = (Double.parseDouble(planetA.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X))
					* (Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y))
					- (Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X))
							* (Double.parseDouble(planetA.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y));

			// Distancia B
			double distanceB = (Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X))
					* (Double.parseDouble(planetC.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y))
					- (Double.parseDouble(planetC.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X))
							* (Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y));

			// Distancia C
			double distanceC = (Double.parseDouble(planetC.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X))
					* (Double.parseDouble(planetA.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y))
					- (Double.parseDouble(planetA.getLongitude()) - Double.parseDouble(Constantconfig.SOL_X))
							* (Double.parseDouble(planetC.getLatitude()) - Double.parseDouble(Constantconfig.SOL_Y));

			if (distanceA < 0 && distanceB < 0 && distanceC < 0) {
				result = true;
			} else if (distanceA > 0 && distanceB > 0 && distanceC > 0) {
				result = true;
			}

		} catch (Exception e) {
			logger.info("Ocurrio un error en el metodo sunInsideTheTriangle: " + e.toString());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean planetsAlignedWithTheSun(Coordinate planetA, Coordinate planetB) {
		boolean result = false;
		try {
			// Verifico que el sol este alineado con los planetas
			double A = Double.parseDouble(planetB.getLongitude()) - Double.parseDouble(planetA.getLongitude());
			double B = Double.parseDouble(planetB.getLatitude()) - Double.parseDouble(planetA.getLatitude());

			double C = Double.parseDouble(Constantconfig.SOL_X) - Double.parseDouble(planetA.getLongitude());
			double D = Double.parseDouble(Constantconfig.SOL_Y) - Double.parseDouble(planetA.getLatitude());

			double aligned = (A * D) - (B * C);

			// Si la ecuacion da cero los planetas estan alineados, sino es un
			// triangulo
			result = aligned == 0 ? true : false;
		} catch (Exception e) {
			logger.info("Ocurrio un error en el metodo planetsAlignedWithTheSun: " + e.toString());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void initialize() {
			Planet planetA = new Planet();
			planetA.setCode("FER");
			planetA.setName("FERENGI");
			planetA.setInitialLatitude("500");
			planetA.setInitialLongitude("0");
			planetA.setDisplacement(1);
			planetA.setDirectionOfRotation("RIG");
			planetRepository.save(planetA);
			logger.info("Se guardo planeta A");
			
			Planet planetB = new Planet();
			planetB.setCode("BET");
			planetB.setName("BETASOIDE");
			planetB.setInitialLatitude("2000");
			planetB.setInitialLongitude("0");
			planetB.setDisplacement(3);
			planetB.setDirectionOfRotation("RIG");
			planetRepository.save(planetB);
			logger.info("Se guardo planeta B");
			
			Planet planetC = new Planet();
			planetC.setCode("VUL");
			planetC.setName("VULCANO");
			planetC.setInitialLatitude("1000");
			planetC.setInitialLongitude("0");
			planetC.setDisplacement(5);
			planetC.setDirectionOfRotation("LEF");
			planetRepository.save(planetC);
			logger.info("Se guardo planeta C");
	}

	@Override
	public void clean() {
		planetRepository.clean();
	}
}
