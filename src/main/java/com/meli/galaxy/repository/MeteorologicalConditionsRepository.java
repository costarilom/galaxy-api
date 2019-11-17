package com.meli.galaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.meli.galaxy.entity.MeteorologicalConditions;

@Repository
public interface MeteorologicalConditionsRepository extends JpaRepository<MeteorologicalConditions, Integer>{

}
