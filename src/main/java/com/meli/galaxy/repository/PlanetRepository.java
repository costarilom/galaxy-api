package com.meli.galaxy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meli.galaxy.entity.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer>{

	@Query("SELECT p FROM planet p WHERE code = :code")
	Planet findPlanetByCode(@Param("code") String code);
	
	@Query("SELECT p FROM planet p")
	List<Planet> findAllPlanet();
}
