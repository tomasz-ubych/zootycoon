package app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import app.entity.AnimalEntity;

@Repository
public interface AnimalRepository extends PagingAndSortingRepository<AnimalEntity, Long> {
	AnimalEntity findById(Long id);
	@Query(value = "SELECT * FROM ANIMAL_ENTITY WHERE OWNER_ENTITY_ID = :id ORDER BY rand() \n#pageable\n"
			    ,countQuery = "SELECT count(*) FROM ANIMAL_ENTITY WHERE OWNER_ENTITY_ID = :id", nativeQuery=true)
	Page<AnimalEntity> findByOwnerEntityId(@Param("id") Long id, Pageable pageable);
	
	@Query(value="Select * FROM ANIMAL_ENTITY", nativeQuery=true)
	List<AnimalEntity> findListOfAll();
	AnimalEntity findByname(String name);
}
