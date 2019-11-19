package com.meli.galaxy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meli.galaxy.entity.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer>{

	@Query("SELECT p FROM Planet p WHERE p.code = :code")
	Planet findPlanetByCode(@Param("code") String code);
	
	@Query("SELECT p FROM Planet p")
	List<Planet> findAllPlanet();
	
	@Modifying
	@Query("DELETE FROM Planet")
	void clean();
}
