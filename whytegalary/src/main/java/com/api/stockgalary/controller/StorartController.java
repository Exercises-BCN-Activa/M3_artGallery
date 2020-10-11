package com.api.stockgalary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.stockgalary.dto.Storart;
import com.api.stockgalary.service.StorartService;

final class StorartController {
	
	StorartController() {}

	@Autowired
	private StorartService service;

	Storart keep(Storart storart) {
		return service.saveOne(storart);
	}

	List<Storart> find() {
		return service.readAll();
	}

	Storart find(Long id) {
		return service.readOne(id);
	}

	Storart update(Long id, Storart storart) {
		return service.updateOne(id, storart);
	}

	void delete(Long id) {
		service.deleteOne(id);
	}

	String ERASE(String password) {
		return service.deleteAllAndEverything(password);
	}

	List<Storart> searchByName(String name) {
		return service.readByName(name);
	}

	List<Storart> searchByCapacityBetween(Integer min, Integer max) {
		return service.readByCapacityBetween(min, max);
	}

	List<Storart> searchByCapacityLess(Integer capacity) {
		return service.readByCapacityLessThan(capacity);
	}

	List<Storart> searchByCapacityGreater(Integer capacity) {
		return service.readByCapacityGreaterThan(capacity);
	}

	List<Storart> searchByArtist(String name) {
		return service.readByArtifactyAuthor(name);
	}

	List<Storart> searchByTitle(String title) {
		return service.readByArtifactyTitle(title);
	}

}
