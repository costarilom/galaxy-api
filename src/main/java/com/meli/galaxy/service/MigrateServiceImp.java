package com.meli.galaxy.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.meli.galaxy.entity.Migrate;
import com.meli.galaxy.repository.MigrateRepository;

@Service
public class MigrateServiceImp implements MigrateService {

	@Resource
	MigrateRepository migrateRepository;

	@Override
	public Migrate getMigrateByType(String type) {
		return migrateRepository.findMigrateByType(type);
	}

	@Override
	public Migrate save(Migrate migrate) {
		return migrateRepository.save(migrate);
	}

	@Override
	public void clean() {
		migrateRepository.clean();
	}
}
