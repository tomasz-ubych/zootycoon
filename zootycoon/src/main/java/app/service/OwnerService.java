package app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import app.entity.OwnerEntity;


public interface OwnerService {
	Page<OwnerEntity>findAll(Pageable pageable);
	OwnerEntity findById(Long id);
	OwnerEntity save(OwnerEntity owner);
	void delete(OwnerEntity owner, Pageable pageable);
}

