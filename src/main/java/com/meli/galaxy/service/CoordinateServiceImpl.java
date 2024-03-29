package com.meli.galaxy.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.meli.galaxy.config.Constantconfig;
import com.meli.galaxy.dto.PlanetDto;
import com.meli.galaxy.entity.Coordinate;
import com.meli.galaxy.entity.Migrate;
import com.meli.galaxy.entity.Planet;
import com.meli.galaxy.repository.CoordinateRepository;

@Service
public class CoordinateServiceImpl implements CoordinateService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	UtilService utilService;

	@Resource
	PlanetService planetService;

	@Resource
	MigrateService migrateService;

	@Resource
	CoordinateRepository coordinateRepository;
	
	@Resource
	WeatherService weatherService;

	@Override
	public void generateCoordinatesAll() {
		// Consulto si ya se hizo una migracion total
		if (migrateService.getMigrateByType(Constantconfig.ALL) == null) {
			// Obtengo el dia actual para sumarle los 10 años
			Date dateFrom = utilService.stringToDate(Constantconfig.dateFrom);
			
			logger.info("El dia actual es " + dateFrom + "\n");
					
			// Hago el insert con status pendiente
			Migrate migrate = new Migrate();
			migrate.setType(Constantconfig.ALL);
			migrate.setStatus(Constantconfig.STATUS_PEN);
			migrate.setDateFrom(new Date());
			migrateService.save(migrate);
			
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFrom);
			calendar.add(Calendar.YEAR, Constantconfig.YEAR_MAX);

			Date dateTo = calendar.getTime();

			logger.info("Inserto coordenadas desde el dia " + dateFrom + " al dia " + dateTo + "\n");

			// Insertar las coordenadas iniciales de cada planeta
			insertInitialCoordinate();

			// Comienzo la iteracion por dia
			while (dateFrom.getTime() < dateTo.getTime()) {
				// controlo de no haber llegado a la fecha de fin
				if (!dateFrom.after(dateTo)) {
					// Incrementas un dia
					dateFrom = utilService.addDay(dateFrom, "1");

					// Llamar al meto de rotacion de planetas
					rotatePlanet(dateFrom);
					
					logger.info("Se procesa el dia " + dateFrom + "\n");
				}
			}

			// hago el update a status END
			migrate.setStatus(Constantconfig.STATUS_END);
			migrate.setDateTo(new Date());
			migrateService.save(migrate);
			logger.info("Finaliza la generacion de coordenadas para los planes Ferengi, Betasoide y Vulcano");
		}else{
			logger.info("La generacion de coordenadas ya se encuentra realizada y disponible para ser consultada");
		}
		
	}

	private void rotatePlanet(Date date) {
		List<Planet> planets = planetService.getAllPlanet();

		for (Planet planet : planets) {
			PlanetDto planetDto = new PlanetDto();
			//Buco las ultimas coordenadas para el planeta
			List<Coordinate> coordinate = getCoordinateByPlanetId(planet.getId());
			if (coordinate != null && !coordinate.isEmpty()) {
				//Obtengo una lista de coordenadas, pero la que importa es la ultima
				planetDto.setId(coordinate.get(0).getId());
				planetDto.setLatitude(coordinate.get(0).getLatitude());
				planetDto.setLongitude(coordinate.get(0).getLongitude());
				planetDto.setDisplacement(planet.getDisplacement());
			} 

			/*
			 * Consulto cual es el desplazamiento del planeta y realizo la
			 * rotacion (Hacia la derecha o hacia la izquierda)
			 */
			double[] coordinateNew = null;
			if (planet.getDirectionOfRotation().equalsIgnoreCase(Constantconfig.RIGHT)) {
				coordinateNew = rightRotation(planetDto);
			} else {
				coordinateNew = leftRotation(planetDto);
			}
			
			// Ingreso las nuevas coordenadas
			if(coordinateNew != null){
				createCoordinate(date, Double.toString(coordinateNew[0]), Double.toString(coordinateNew[1]), planet);
			}
			else{
				logger.info("No se pudieron obtener las nuevas coordenadas para el planeta " + planetDto.getId() );
			}
		}
		
		//Inserto la condicion meteorologia para el dia 
		weatherService.saveWeatherByDate(date);
	}

	private void insertInitialCoordinate() {
		List<Planet> planets = planetService.getAllPlanet();
		Date date = utilService.stringToDate(Constantconfig.dateFrom);
		for (Planet planet : planets) {
			createCoordinate(date, planet.getInitialLongitude(), planet.getInitialLatitude(), planet);
		}
		//Genero la condicion meteorologica incial
		weatherService.saveWeatherByDate(date);
	}

	@Override
	public Coordinate save(Coordinate coordinate) {
		return coordinateRepository.save(coordinate);
	}

	@Override
	public List<Coordinate> getCoordinateByPlanetId(Integer planetId) {
		logger.info("Se buscan las coordenadas del planeta " + planetId);
		return coordinateRepository.findCoordinatesByPlanetId(planetId);
	}

	private Coordinate createCoordinate(Date date, String longitude, String latitude, Planet planet) {
		Coordinate coordinate = new Coordinate();
		coordinate.setDate(date);
		coordinate.setYear(utilService.getYearByDate(date));
		coordinate.setLatitude(latitude);
		coordinate.setLongitude(longitude);
		coordinate.setPlanet(planet);
		
		logger.info("Se crean las coordenadas para el planeta " + planet.getId() + "para el dia" + date);

		return save(coordinate);
	}
	
	public double[] rightRotation(PlanetDto planetDto){
		double X = Double.valueOf(planetDto.getLongitude());
		double Y = Double.valueOf(planetDto.getLatitude());		
		double displacement = Math.toRadians(planetDto.getDisplacement());
		 		
		//Formula para obtener X' 
		double XP = X * Math.cos(displacement) + Y * Math.sin(displacement);
		//Formula para obtener Y'
		double YP = Y * Math.cos(displacement) - X * Math.sin(displacement);
		
		double[] coordinate = {XP, YP};    

		logger.info("Se rota el planeta " + planetDto.getId() + " hacia la derecha");

	    return coordinate;
	}
	
	public double[] leftRotation(PlanetDto planetDto){
		double X = Double.valueOf(planetDto.getLongitude());
		double Y = Double.valueOf(planetDto.getLatitude());
		double displacement = Math.toRadians(planetDto.getDisplacement());
		
		//Formula para obtener X'
		double XP = X * Math.cos(displacement) - Y * Math.sin(displacement);
		//Formula para obtener Y'
		double YP = X * Math.sin(displacement) + Y * Math.cos(displacement);
				
		double[] coordinate = {XP, YP};    

		logger.info("Se rota el planeta " + planetDto.getId() + " hacia la izquierda");

	    return coordinate;
	}

	@Override
	public List<Coordinate> getCoordinatesByDate(Date date) {
		return coordinateRepository.findCoordinatesByDate(utilService.getDateFrom(date), utilService.getDateTo(date));
	}

	@Override
	public void clean() {
		coordinateRepository.clean();
	}

}
