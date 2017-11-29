package app.service;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import app.entity.AnimalEntity;
import app.entity.OwnerEntity;
import app.repository.AnimalRepository;
import app.repository.OwnerRepository;
@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
	@Autowired 
	AnimalRepository animalRepository;
	@Autowired
	OwnerRepository ownerRepository;
	
	@Override
	public Page<AnimalEntity> findAll(Pageable pageable) {
		 Page<AnimalEntity>animalEntityList = animalRepository.findAll(pageable);
		 return animalEntityList;
		}

	@Override
	public AnimalEntity findById(Long id) {
		AnimalEntity animal = animalRepository.findById(id);
		return animal;
	}
	@Override
	public AnimalEntity save(AnimalEntity animal) {
		animalRepository.save(animal);
		return animal;
	}
	
	@Override
	public AnimalEntity saveToParticularOwner(AnimalEntity animal, OwnerEntity owner) {
		animal.setOwnerEntity(owner);
		animalRepository.save(animal);
		return animal;
	}

	@Override
	public void delete(AnimalEntity animal) {
		animalRepository.delete(animal);
	}

	@Override
	public Page<AnimalEntity> findByOwnerEntityId(Long id, Pageable pageable) {
		 Page<AnimalEntity>animalEntityList = animalRepository.findByOwnerEntityId(id, pageable);
		return animalEntityList;
	}

	@Override
	public List<AnimalEntity> findListOfAllAnimals() {
		return animalRepository.findListOfAll();
	}

	@Override
	public AnimalEntity findByName(String name) {
		return animalRepository.findByname(name);
	}
}
