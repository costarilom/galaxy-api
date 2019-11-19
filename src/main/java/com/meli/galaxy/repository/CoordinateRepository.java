package com.meli.galaxy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meli.galaxy.entity.Coordinate;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {
	
	@Query("SELECT c FROM Coordinate c WHERE c.planet.id = :planetId ORDER BY c.id DESC")
	List<Coordinate> findCoordinatesByPlanetId(@Param("planetId") Integer planetId);
	
	@Query("SELECT c FROM Coordinate c WHERE c.date BETWEEN :dateFrom AND :dateTo")
	List<Coordinate> findCoordinatesByDate(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
	
	@Modifying
	@Query("DELETE FROM Coordinate")
	void clean();
}
