/*package app;

import java.util.List;

import org.springframework.stereotype.Component;

import app.entity.AnimalEntity;
import app.entity.OwnerEntity;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class OrikaMapper {
	MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	
	BoundMapperFacade<AnimalEntity,Animal> mapper = mapperFactory.getMapperFacade(AnimalEntity.class, Animal.class);
	
	BoundMapperFacade<OwnerEntity,Owner> mapper2 = mapperFactory.getMapperFacade(OwnerEntity.class, Owner.class);

	public Animal convertToAnimal(AnimalEntity animalEntity) {
		return mapper.map(animalEntity);
	}
	public AnimalEntity convertToAnimalEntity(Animal animal) {
		return mapper.mapReverse(animal);
	}
	public Owner convertToOwner(OwnerEntity ownerEntity) {
		return mapper2.map(ownerEntity);
	}
	public OwnerEntity convertToOwnerEntity(Owner owner) {
		return mapper2.mapReverse(owner);
	}


	
	
	}
}*/