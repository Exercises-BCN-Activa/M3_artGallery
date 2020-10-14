package com.api.stockgalary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.stockgalary.dto.Storart;
import com.api.stockgalary.service.StorartService;
import com.api.stockgalary.util.*;

/**
 * final class that controls all methods referring to the DTO Storart entity and
 * provides access routes to these methods via api rest. Invokes Service by auto
 * wired
 * 
 * @author FaunoGuazina
 */
@RestController
@RequestMapping("/api")
final class StorartController {

	/**
	 * standard empty constructors with access only by package
	 */
	StorartController() {
	}
	
// SERVICE CLASS variable

	@Autowired
	private StorartService service;
	
// Methods CRUD

	@PostMapping("/galleries")
	Storart keep(@RequestBody Storart storart) {
		
		return service.saveOne(storart);
		
	}

	@GetMapping("/galleries")
	List<Storart> find() {
		
		return service.readAll();
		
	}

	@PutMapping("/galleries")
	Storart update(@RequestParam Long id, @RequestBody Storart updated) {
		
		Storart toUpdate = service.readOne(id); //catch the object to update by ID
		boolean ok = false;						//variable to confirm the update
		
		if (toUpdate != null && updated != null) {	//conditional if there is an update and what to update
			
			if (updated.getName() != null && !updated.getName().isEmpty()			//if name not null or empty
					&& !updated.getName().equalsIgnoreCase(toUpdate.getName())) {	//or equal to the current
				
				toUpdate.setName(Inputs.toTitleCase(updated.getName()));			//do the update
				ok = true;															//set true to update variable control
			}
			
			if (updated.getCapacity() != null && 									//if capacity not null
					updated.getCapacity() != toUpdate.getCapacity()) {				//or equal to the current
				
				toUpdate.setCapacity(updated.getCapacity());						//do the update
				ok = true;															//set true to update variable control
			}
		}
		
		return (toUpdate != null && ok) ? service.updateOne(toUpdate) : null;		//if OK and not null update
																					//or return null
	}

	@DeleteMapping("/galleries")
	void delete(@RequestParam Long id) {
		
		service.deleteOne(id);
		
	}
	
// Methods by DAO class

	/**
	 * this method brings together all the search methods for the service cover, 
	 * including one of the simple CRUD (read by id).
	 * 
	 * @param by set type of search (name, id, capacity, author, title) 
	 * @param name for name, author and title search
	 * @param id for id search
	 * @param min for between and greater than capacity search
	 * @param max for between and less than capacity search
	 * @return List<Storart>
	 */
	@SuppressWarnings("serial")
	@GetMapping("/galleries/search")
	List<Storart> search(@RequestParam String by, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long id, @RequestParam(required = false) Integer min,
			@RequestParam(required = false) Integer max) {

		switch (by.toLowerCase()) { //Ternaries or if = if the variables are null it returns null

		case "name":
			
			return name == null ? null 
					: service.readByName(Inputs.toTitleCase(name));

		case "id":
			
			return (id == null) ? null 
					: new ArrayList<Storart>() 
						{{add(service.readOne(id));}};	//inner class to add item in that arraylist

		case "capacity":
			
			if (min == null && max == null) {
				return null;
			} 
			
			else if (min != null && max != null) {
				return service.readByCapacityBetween(min, max);
			} 
			
			else if (min != null && max == null) {
				return service.readByCapacityGreaterThan(min);
			} 
			
			else if (min == null && max != null) {
				return service.readByCapacityLessThan(max);
			}

		case "author":
			
			return name == null ? null 
					: service.readByArtifactyAuthor(Inputs.toTitleCase(name));

		case "title":
			
			return name == null ? null 
					: service.readByArtifactyTitle(Inputs.toTitleCase(name));

		default:
			
			return null;
		}
	}

	@DeleteMapping("/galleries/run")
	String ERASE(@RequestParam(required = false) String password) {
		
		return service.deleteAllAndEverything(password);
		
	}

}
