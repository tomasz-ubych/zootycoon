package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.entity.OwnerEntity;
import app.service.AnimalService;
import app.service.OwnerService;

@Controller
@CrossOrigin
public class OwnerController {
	@Autowired
	OwnerService ownerService;
	@Autowired
	AnimalService animalService;
	
	@RequestMapping(value="/owner", method = RequestMethod.GET)
	public ResponseEntity<Page<OwnerEntity>>getAllOwners(Pageable pageable)
	{
		return new ResponseEntity<Page<OwnerEntity>>(ownerService.findAll(pageable),HttpStatus.OK);	
	}
   
	@RequestMapping(value="/owner/{id}", method = RequestMethod.GET)
	public ResponseEntity<OwnerEntity>getOwnerById(@PathVariable String id){
		OwnerEntity ownerToReturn = ownerService.findById(Long.valueOf(id));
		if (ownerToReturn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OwnerEntity>(ownerToReturn,HttpStatus.OK);
	}
	
	@RequestMapping(value="/owner", method=RequestMethod.POST)
	public ResponseEntity<OwnerEntity> createOwner(@RequestBody OwnerEntity owner){
		return new ResponseEntity<OwnerEntity>(ownerService.save(owner),HttpStatus.OK);	
	
	}
	
	@RequestMapping(value="/owner/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOwnerById(@PathVariable String id, Pageable pageable) {
		OwnerEntity ownerToDelete = ownerService.findById(Long.valueOf(id));
		if (ownerToDelete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ownerService.delete(ownerToDelete, pageable);
		 return ResponseEntity.noContent().build();	
	}
	@RequestMapping(value="/owner/{id}", method=RequestMethod.PUT)
	public ResponseEntity<OwnerEntity> updateOwner(@PathVariable String id, @RequestBody OwnerEntity owner){
		OwnerEntity ownerToUpdate = ownerService.findById(Long.valueOf(id));
		if (ownerToUpdate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		owner.setId(Long.valueOf(id));
		ownerService.save(owner);
		return new ResponseEntity<OwnerEntity>(owner, HttpStatus.OK);
	}
}
