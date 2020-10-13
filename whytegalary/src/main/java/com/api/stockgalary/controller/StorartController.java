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

@RestController
@RequestMapping("/api")
final class StorartController {

	StorartController() {
	}

	@Autowired
	private StorartService service;

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
		Storart toUpdate = service.readOne(id);
		boolean ok = false;
		if (toUpdate != null && updated != null) {
			if (updated.getName() != null && !updated.getName().isEmpty()
					&& !updated.getName().equalsIgnoreCase(toUpdate.getName())) {
				toUpdate.setName(Inputs.toTitleCase(updated.getName()));
				ok = true;
			}
			if (updated.getCapacity() != null && updated.getCapacity() != toUpdate.getCapacity()) {
				toUpdate.setCapacity(updated.getCapacity());
				ok = true;
			}
		}
		return (toUpdate != null && ok) ? service.updateOne(toUpdate) : null;
	}

	@DeleteMapping("/galleries")
	void delete(@RequestParam Long id) {
		service.deleteOne(id);
	}

	@SuppressWarnings("serial")
	@GetMapping("/galleries/search")
	List<Storart> search(@RequestParam String by, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long id, @RequestParam(required = false) Integer min,
			@RequestParam(required = false) Integer max) {

		switch (by.toLowerCase()) {

		case "name":
			return name == null ? null : service.readByName(Inputs.toTitleCase(name));

		case "id":
			return (id == null) ? null : new ArrayList<Storart>() {
				{
					add(service.readOne(id));
				}
			};

		case "capacity":
			if (min == null && max == null) {
				return null;
			} else if (min != null && max != null) {
				return service.readByCapacityBetween(min, max);
			} else if (min != null && max == null) {
				return service.readByCapacityGreaterThan(min);
			} else if (min == null && max != null) {
				return service.readByCapacityLessThan(max);
			}

		case "author":
			return name == null ? null : service.readByArtifactyAuthor(Inputs.toTitleCase(name));

		case "title":
			return name == null ? null : service.readByArtifactyTitle(Inputs.toTitleCase(name));

		default:
			return null;
		}
	}

	@DeleteMapping("/galleries/run")
	String ERASE(@RequestParam(required = false) String password) {
		return service.deleteAllAndEverything(password);
	}

}
