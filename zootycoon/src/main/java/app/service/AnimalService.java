package app.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import app.entity.AnimalEntity;
import app.entity.OwnerEntity;


public interface AnimalService {
	Page<AnimalEntity>findAll(Pageable pageable);
	AnimalEntity findById(Long id);
	AnimalEntity save(AnimalEntity animal);
	void delete(AnimalEntity animal);
	Page<AnimalEntity>findByOwnerEntityId(Long id, Pageable pageable);
	AnimalEntity saveToParticularOwner(AnimalEntity animal, OwnerEntity owner);
	List<AnimalEntity>findListOfAllAnimals();
	AnimalEntity findByName(String name);
}
