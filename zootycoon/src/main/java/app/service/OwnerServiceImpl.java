package app.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import app.entity.AnimalEntity;
import app.entity.OwnerEntity;
import app.repository.OwnerRepository;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
	@Autowired 
	OwnerRepository ownerRepository;
	@Autowired
	AnimalService animalService;
	
	public Page<OwnerEntity> findAll(Pageable pageable) {
		 Page<OwnerEntity>ownerEntityList =  ownerRepository.findAll(pageable);
		 return ownerEntityList;
		}

	public OwnerEntity findById(Long id) {
		OwnerEntity owner = ownerRepository.findById(id);
		return owner;
	}
	public OwnerEntity save(OwnerEntity owner) {
		ownerRepository.save(owner);
		return owner;
	}

	public void delete(OwnerEntity owner,Pageable pageable) {
		Page<AnimalEntity>pageOfAnimals = animalService.findByOwnerEntityId(owner.getId(), pageable);
		for (AnimalEntity ae: pageOfAnimals) {
			ae.setOwnerEntity(null);
		}
		ownerRepository.delete(owner);
	}
}


