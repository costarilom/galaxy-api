package com.meli.galaxy.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.dto.PlanetDto;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Migrate;
import com.meli.galaxy.entity.Planet;
import com.meli.galaxy.repository.CoordinateRepository;

@Service
public class CoordinateServiceImpl implements CoordinateService {

	@Resource
	UtilService utilService;

	@Resource
	PlanetService planetService;

	@Resource
	MigrateService migrateService;

	@Resource
	CoordinateRepository coordinateRepository;

	@Override
	public void generateCoordinatesAll() {
		// Consulto si ya se hizo una migracion total
		if (migrateService.getMigrateByType(Constantconfig.ALL) == null) {
			// Obtengo el dia actual para sumarle los 10 años
			Date dateFrom = new Date();
			System.out.println("El dia actual es " + dateFrom + "\n");
						
			// Hago el insert con status pen
			Migrate migrate = new Migrate();
			migrate.setType(Constantconfig.ALL);
			migrate.setStatus(Constantconfig.STATUS_PEN);
			migrate.setDate(dateFrom);
			migrateService.save(migrate);
			
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFrom);
			calendar.add(Calendar.YEAR, Constantconfig.YEAR_MAX);

			Date dateTo = calendar.getTime();

			System.out.println("Inserto coordenadas desde el dia " + dateFrom + " al dia " + dateTo + "\n");

			// Insertar en coodinate las coordenadas iniciales de cada planeta
			insertInitialCoordinate();

			// Comienzo la iteracion por dia
			while (dateFrom.getTime() < dateTo.getTime()) {
				// controlo de no haber llegado a la fecha de fin
				if (!dateFrom.after(dateTo)) {
					// Incrementas un dia (86400000 es un dia en milisegundos)
					dateFrom.setTime((long) dateFrom.getTime() + (86400000));

					// Llamar al meto de rotacion de planetas
					rotatePlanet(dateFrom);

					// Hacer el insert en la tabla de coordenadas

					System.out.print("Se procesa el dia " + dateFrom + "\n");

				}
			}

			// hago el update a status END
			migrate.setStatus(Constantconfig.STATUS_END);
			migrateService.save(migrate);
			System.out.println("Finaliza la generacion de coordenadas para los planes Ferengi, Betasoide y Vulcano");
		}
	}

	private void rotatePlanet(Date date) {
		List<Planet> planets = planetService.getAllPlanet();

		for (Planet planet : planets) {
			PlanetDto planetDto = new PlanetDto();
			/*
			 * Consulto si tengo alguna coordenada persistida para ese planeta,
			 * sino uso las coordenadas iniciales
			 */
			List<Coordinate> coordinate = getCoordinateByPlanetId(planet.getId());
			// Si encontre coordenadas las uso, sino uso las iniciales
			if (coordinate != null && !coordinate.isEmpty()) {
				planetDto.setLatitude(coordinate.get(0).getLatitude());
				planetDto.setLongitude(coordinate.get(0).getLongitude());
				planetDto.setDisplacement(planet.getDisplacement());
			} else {
				planetDto.setLatitude(planet.getInitialLatitude());
				planetDto.setLongitude(planet.getInitialLongitude());
				planetDto.setDisplacement(planet.getDisplacement());
			}

			/*
			 * Consulto cual es el desplazamiento del planeta y realizo la
			 * rotacion (Hacia la derecha o hacia la izquierda)
			 */
			if (planet.getDirectionOfRotation().equalsIgnoreCase(Constantconfig.RIGHT)) {
				double[] coordinateNew = utilService.rightRotation(planetDto);
				
				// Ingreso las nuevas coordenadas
				createCoordinate(date, Double.toString(coordinateNew[0]), Double.toString(coordinateNew[1]), planet);
			} else {
				double[] coordinateNew = utilService.leftRotation(planetDto);
				
				// Ingreso las nuevas coordenadas
				createCoordinate(date, Double.toString(coordinateNew[0]), Double.toString(coordinateNew[1]), planet);
			}
		}
	}

	private void insertInitialCoordinate() {
		List<Planet> planets = planetService.getAllPlanet();

		for (Planet planet : planets) {
			createCoordinate(new Date(), planet.getInitialLatitude(), planet.getInitialLongitude(), planet);
		}
	}

	@Override
	public Coordinate save(Coordinate coordinate) {
		return coordinateRepository.save(coordinate);
	}

	@Override
	public List<Coordinate> getCoordinateByPlanetId(Integer planetId) {
		return coordinateRepository.findCoordinateByPlanetId(planetId);
	}

	private Coordinate createCoordinate(Date date, String latitude, String longitude, Planet planet) {
		Coordinate coordinate = new Coordinate();
		coordinate.setDate(date);
		coordinate.setYear(utilService.getYearByDate(date));
		coordinate.setLatitude(latitude);
		coordinate.setLongitude(longitude);
		coordinate.setPlanet(planet);

		return save(coordinate);
	}
}
