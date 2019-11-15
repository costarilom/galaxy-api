package com.meli.galaxy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meli.galaxy.entity.Coordinate;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {
	
	@Query("SELECT c FROM Coordinate WHERE c.planet.id = :planetId ORDER BY c.id DESC")
	List<Coordinate> findCoordinateByPlanetId(@Param("planetId") Integer planetId);
}
