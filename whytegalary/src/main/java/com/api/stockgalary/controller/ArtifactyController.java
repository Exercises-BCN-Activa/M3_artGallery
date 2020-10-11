package com.api.stockgalary.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.stockgalary.dto.Artifacty;
import com.api.stockgalary.service.ArtifactyService;

class ArtifactyController {
	
	ArtifactyController() {}
	
	@Autowired
	private ArtifactyService service;

	Artifacty keep(Artifacty artifacty) {
		return service.saveOne(artifacty);
	}

	List<Artifacty> find() {
		return service.readAll();
	}

	Artifacty find(Long id) {
		return service.readOne(id);
	}

	Artifacty update(Long id, Artifacty artifacty) {
		return service.updateOne(id, artifacty);
	}

	void delete(Long id) {
		service.deleteOne(id);
	}

	String ERASE(String password) {
		return service.deleteAllAndEverything(password);
	}
	
	List<Artifacty> searchByAuthor(String author) {
		return service.readByAuthor(author);
	}
	
	List<Artifacty> searchByTitle(String title) {
		return service.readByTitle(title);
	}
	
	List<Artifacty> searchByShopName(String name) {
		return service.readByShopName(name);
	}
	
	List<Artifacty> searchByShopId(Long id) {
		return service.readByShopId(id);
	}
	
	List<Artifacty> searchByValueBetween(BigDecimal min, BigDecimal max) {
		return service.readByValueBetween(min, max);
	}
	
	List<Artifacty> searchByValueLess(BigDecimal value) {
		return service.readByValueLessThan(value);
	}
	
	List<Artifacty> searchByValueGreater(BigDecimal value) {
		return service.readByValueGreaterThan(value);
	}

}
