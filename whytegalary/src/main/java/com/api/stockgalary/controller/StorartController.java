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

@RestController
@RequestMapping("/api")
final class StorartController {

	StorartController() {}

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
		toUpdate.setName(updated.getName());
		toUpdate.setCapacity(updated.getCapacity());
		return service.updateOne(toUpdate);
	}

	@DeleteMapping("/galleries")
	void delete(@RequestParam Long id) {
		service.deleteOne(id);
	}
	
	@SuppressWarnings("serial")
	@GetMapping("/galleries/search")
	List<Storart> search(@RequestParam String by, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long id, @RequestParam(required = false) Integer capacity,
			@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
		switch (by) {
		case "name":
			return name == null ? null : service.readByName(name);
		case "id":
			return (id == null) ? null : new ArrayList<Storart>() {{add(service.readOne(id));}};
		case "capacity":
			return (min == null && max == null) ? null : service.readByCapacityBetween(min, max);
		case "less":
			return capacity == null ? null : service.readByCapacityLessThan(capacity);
		case "greater":
			return capacity == null ? null : service.readByCapacityGreaterThan(capacity);
		case "author":
			return name == null ? null : service.readByArtifactyAuthor(name);
		case "title":
			return name == null ? null : service.readByArtifactyTitle(name);
		default:
			return null;
		}
	}

	@DeleteMapping("/galleries/run")
	String ERASE(@RequestParam String password) {
		return service.deleteAllAndEverything(password);
	}
	
	@GetMapping("/galleries/")
	Storart find(@RequestParam Long id) {
		return service.readOne(id);
	}

	@GetMapping("/galleries/by")
	List<Storart> searchByName(@RequestParam String name) {
		return service.readByName(name);
	}

	@GetMapping("/galleries/capacity")
	List<Storart> searchByCapacityBetween(@RequestParam Integer min, @RequestParam Integer max) {
		return service.readByCapacityBetween(min, max);
	}

	@GetMapping("/galleries/lessThan")
	List<Storart> searchByCapacityLess(@RequestParam Integer capacity) {
		return service.readByCapacityLessThan(capacity);
	}

	@GetMapping("/galleries/greaterThan")
	List<Storart> searchByCapacityGreater(@RequestParam Integer capacity) {
		return service.readByCapacityGreaterThan(capacity);
	}

	@GetMapping("/galleries/byAuthor")
	List<Storart> searchByArtist(@RequestParam String name) {
		return service.readByArtifactyAuthor(name);
	}

	@GetMapping("/galleries/byTitle")
	List<Storart> searchByTitle(@RequestParam String name) {
		return service.readByArtifactyTitle(name);
	}
}
