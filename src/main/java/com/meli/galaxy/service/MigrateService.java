package com.meli.galaxy.service;

import org.springframework.data.repository.query.Param;
import com.meli.galaxy.entity.Migrate;

public interface MigrateService {

	Migrate getMigrateByType(@Param("type") String type);
	
	Migrate save(Migrate migrate);
	
	void clean();
}
