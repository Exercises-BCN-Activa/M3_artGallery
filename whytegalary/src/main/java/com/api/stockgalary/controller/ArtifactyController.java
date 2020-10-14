package com.api.stockgalary.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.stockgalary.dto.Artifacty;
import com.api.stockgalary.dto.Storart;
import com.api.stockgalary.service.ArtifactyService;
import com.api.stockgalary.util.*;

/**
 * final class that controls all methods referring to the DTO Artifacty entity
 * and provides access routes to these methods via api rest. Invokes Service by
 * auto wired
 * 
 * @author FaunoGuazina
 */
@RestController
@RequestMapping("/api")
final class ArtifactyController {

	/**
	 * standard empty constructors with access only by package
	 */
	ArtifactyController() {
	}

// SERVICE CLASS variable

	@Autowired
	private ArtifactyService service;

// Methods CRUD

	@PostMapping("/artefacts")
	Artifacty keep(@RequestParam Storart id, @RequestBody Artifacty artifacty) {
		
		artifacty.setShop(id);
		
		return service.saveOne(artifacty);
		
	}

	@GetMapping("/artefacts")
	List<Artifacty> find() {
		
		return service.readAll();
		
	}

	@PutMapping("/artefacts")
	Artifacty update(@RequestParam Long id, @RequestParam(required = false) Storart shopId,
			@RequestParam(required = false) String author, @RequestParam(required = false) String title,
			@RequestParam(required = false) BigDecimal value) {
		
		Artifacty toUpdate = service.readOne(id);	//catch the object to update by ID
		boolean ok = false;							//variable to confirm the update
		
		if (toUpdate != null) {	//conditional if there is something to update
			
			if (author != null && !author.isEmpty() 						//if author not null or empty
					&& !author.equalsIgnoreCase(toUpdate.getAuthor())) {	//or equal to the current
				
				toUpdate.setAuthor(Inputs.toTitleCase(author));				//do the update
				toUpdate.setUpdate();										// set method update register
				ok = true;													//set true to update variable control
			}
			
			if (title != null && !title.isEmpty() 							//if title not null or empty
					&& !title.equalsIgnoreCase(toUpdate.getTitle())) {		//or equal to the current
				
				toUpdate.setTitle(Inputs.toTitleCase(title));				//do the update
				toUpdate.setUpdate();										// set method update register
				ok = true;													//set true to update variable control
			}
			
			if (value != null && value != toUpdate.getValue()) {			//if value not null or equal to the current
				
				toUpdate.setValue(value);									//do the update
				toUpdate.setUpdate();										// set method update register
				ok = true;													//set true to update variable control
			}
			
			if (shopId != null && !shopId.equals(toUpdate.getShop())) {		//if shop not null or equal to the current
				
				toUpdate.setShop(shopId);									//do the update
				toUpdate.setUpdate();										// set method update register
				ok = true;													//set true to update variable control
			}
		}

		return (toUpdate != null && ok) ? service.updateOne(toUpdate) : null;
	}

	@DeleteMapping("/artefacts")
	void delete(@RequestParam Long id) {
		
		service.deleteOne(id);
		
	}

// Methods by DAO class

	/**
	 * this method brings together all the search methods for the service cover,
	 * including one of the simple CRUD (read by id).
	 * 
	 * @param by   set type of search (id, author, title, shop[id,name], value)
	 * @param name for author, title and shop name search
	 * @param id   for id search
	 * @param min  for between and greater than value search
	 * @param max  for between and less than value search
	 * @return List<Artifacty>
	 */
	@SuppressWarnings("serial")
	@GetMapping("/artefacts/search")
	List<Artifacty> search(@RequestParam String by, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long id, @RequestParam(required = false) BigDecimal min,
			@RequestParam(required = false) BigDecimal max) {

		switch (by.toLowerCase()) { //Ternaries or if = if the variables are null it returns null

		case "id":
			return (id == null) ? null 
					: new ArrayList<Artifacty>()
						{{add(service.readOne(id));}}; //inner class to add item in that arraylist

		case "author":
			return name == null ? null 
					: service.readByAuthor(Inputs.toTitleCase(name));

		case "title":
			return name == null ? null 
					: service.readByTitle(Inputs.toTitleCase(name));

		case "shop":
			if (id == null && name == null) {
				return null;
			} 
			
			else if (id != null) {
				return service.readByShopId(id);
			} 
			
			else if (name != null) {
				return service.readByShopName(Inputs.toTitleCase(name));
			}

		case "value":
			if (min == null && max == null) {
				return null;
			} 
			
			else if (min != null && max != null) {
				return service.readByValueBetween(min, max);
			} 
			
			else if (min != null && max == null) {
				return service.readByValueGreaterThan(min);
			} 
			
			else if (min == null && max != null) {
				return service.readByValueLessThan(max);
			}

		default:
			return null;
		}
	}

	@DeleteMapping("/artefacts/burn/{id}")
	String ERASE(@PathVariable(name = "id") Long shopId, 
			@RequestParam(required = false) String password) {
		
		return (shopId == null || password == null) ? null
				: service.deleteAllAndEverything(shopId, password);
		
	}

}
