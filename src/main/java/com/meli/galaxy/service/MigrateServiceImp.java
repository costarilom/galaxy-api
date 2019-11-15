package com.meli.galaxy.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.meli.galaxy.entity.Migrate;
import com.meli.galaxy.repository.MigrateRepository;
import com.meli.galaxy.repository.PlanetRepository;

@Service
public class MigrateServiceImp implements MigrateService {

	@Resource
	MigrateRepository migrateRepository;

	@Override
	public Migrate getMigrateByType(String type) {
		return migrateRepository.findMigrateByType(type);
	}
	


}
