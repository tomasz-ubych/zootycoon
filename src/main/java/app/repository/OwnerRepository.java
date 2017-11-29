package app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import app.entity.OwnerEntity;

@Repository
public interface OwnerRepository extends PagingAndSortingRepository<OwnerEntity, Long> {
	OwnerEntity findById(Long id); 
}
