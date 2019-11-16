package com.meli.galaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.meli.galaxy.entity.Migrate;

@Repository
public interface MigrateRepository extends JpaRepository<Migrate, Integer>{

	@Query("SELECT m FROM Migrate m WHERE m.type = :type")
	Migrate findMigrateByType(@Param("type") String type);
}
