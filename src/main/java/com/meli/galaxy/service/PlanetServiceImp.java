package com.meli.galaxy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
