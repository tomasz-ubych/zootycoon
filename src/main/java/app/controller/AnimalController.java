package app.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.entity.AnimalEntity;
import app.entity.OwnerEntity;
import app.service.AnimalService;
import app.service.OwnerService;

@CrossOrigin
@Controller
public class AnimalController {
	@Autowired
	AnimalService animalService;
	@Autowired
	OwnerService ownerService;

	@RequestMapping(value = "/animal", method = RequestMethod.GET)
	public ResponseEntity<Page<AnimalEntity>> getAllAnimals(Pageable pageable) {
		return new ResponseEntity<Page<AnimalEntity>>(animalService.findAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(value = "/animal/{id}", method = RequestMethod.GET)
	public ResponseEntity<AnimalEntity> getAnimalById(@PathVariable String id) {
		AnimalEntity animalToReturn = animalService.findById(Long.valueOf(id));
		if (animalToReturn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AnimalEntity>(animalToReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/animal", method = RequestMethod.POST)
	public ResponseEntity<AnimalEntity> createAnimal(@RequestBody AnimalEntity animal) {
		return new ResponseEntity<AnimalEntity>(animalService.save(animal), HttpStatus.OK);
	}

	@RequestMapping(value = "/owner/{id}/animal", method = RequestMethod.POST)
	public ResponseEntity<Object> createAnimalToOwner(@RequestBody @Valid AnimalEntity animal, Pageable pageable,
			BindingResult result, @PathVariable String id) {
		OwnerEntity owner = ownerService.findById(Long.valueOf(id));
		if (owner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(errors, HttpStatus.OK);
		} else {
			AnimalEntity animalWithRepeatedName = animalService.findByName(animal.getName());
			if (animalWithRepeatedName != null) {
				return new ResponseEntity<>(Collections.singletonList("name " + animal.getName() + " already exists!"),
						HttpStatus.CONFLICT);
			} else {
				animalService.saveToParticularOwner(animal, owner);
				return new ResponseEntity<Object>(animal, HttpStatus.CREATED);
			}
		}
	}
	
	
	@RequestMapping(value="/animal/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAnimalById(@PathVariable String id) {
		AnimalEntity animalToDelete = animalService.findById(Long.valueOf(id));
		if (animalToDelete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 animalService.delete(animalToDelete);
		 return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(value="/animal/list/{ownerID}", method = RequestMethod.GET)
	public ResponseEntity<Page<AnimalEntity>>getAnimalsByOwner(@PathVariable String ownerID, Pageable pageable)
	{
		return new ResponseEntity<Page<AnimalEntity>>(animalService.findByOwnerEntityId(Long.valueOf(ownerID), pageable),HttpStatus.OK);	
	}
	@RequestMapping(value="/animal/{id}", method = RequestMethod.PUT)
	public ResponseEntity<AnimalEntity>updateAnimal(@PathVariable String id, @RequestBody AnimalEntity currentAnimal){
		AnimalEntity animalToUpdate = animalService.findById(Long.valueOf(id));
		if (animalToUpdate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentAnimal.setId(Long.valueOf(id));;
			return new ResponseEntity<AnimalEntity>(animalService.save(currentAnimal), HttpStatus.OK);
	}
}