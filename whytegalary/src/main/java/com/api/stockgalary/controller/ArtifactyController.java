package com.api.stockgalary.controller;

import java.math.BigDecimal;
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

import com.api.stockgalary.dto.Artifacty;
import com.api.stockgalary.dto.Storart;
import com.api.stockgalary.service.ArtifactyService;

@RestController
@RequestMapping("/api")
final class ArtifactyController {

	ArtifactyController() {}

	@Autowired
	private ArtifactyService service;

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
	Artifacty update(@RequestParam Long id, @RequestBody Artifacty updated, @RequestParam Storart shopId) {
		Artifacty toUpdate = service.readOne(id);
		toUpdate.setAuthor(updated.getAuthor());
		toUpdate.setTitle(updated.getTitle());
		toUpdate.setValue(updated.getValue());
		toUpdate.setShop(shopId);
		toUpdate.setUpdate();
		return service.updateOne(toUpdate);
	}

	@DeleteMapping("/artefacts")
	void delete(@RequestParam Long id) {
		service.deleteOne(id);
	}

	@SuppressWarnings("serial")
	@GetMapping("/artefacts/search")
	List<Artifacty> search(@RequestParam String by, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long id, @RequestParam(required = false) BigDecimal value,
			@RequestParam(required = false) BigDecimal min, @RequestParam(required = false) BigDecimal max) {
		switch (by) {
		case "shop":
			return name == null ? null : service.readByShopName(name);
		case "id":
			return (id == null) ? null : new ArrayList<Artifacty>() {{add(service.readOne(id));}};
		case "value":
			return (min == null && max == null) ? null : service.readByValueBetween(min, max);
		case "less":
			return value == null ? null : service.readByValueLessThan(value);
		case "greater":
			return value == null ? null : service.readByValueGreaterThan(value);
		case "author":
			return name == null ? null : service.readByAuthor(name);
		case "title":
			return name == null ? null : service.readByTitle(name);
		default:
			return null;
		}
	}

	@GetMapping("/artefacts/id")
	Artifacty find(@RequestParam Long id) {
		return service.readOne(id);
	}

	@DeleteMapping("/galleries/burn")
	String ERASE(@RequestParam Long shopId, @RequestParam String password) {
		return service.deleteAllAndEverything(shopId, password);
	}

	@GetMapping("/artefacts/author")
	List<Artifacty> searchByAuthor(@RequestParam String author) {
		return service.readByAuthor(author);
	}

	@GetMapping("/artefacts/title")
	List<Artifacty> searchByTitle(@RequestParam String title) {
		return service.readByTitle(title);
	}

	@GetMapping("/artefacts/shop")
	List<Artifacty> searchByShopName(@RequestParam String name) {
		return service.readByShopName(name);
	}

	@GetMapping("/artefacts/shop/")
	List<Artifacty> searchByShopId(@RequestParam Long id) {
		return service.readByShopId(id);
	}

	@GetMapping("/artefacts/valueBetween")
	List<Artifacty> searchByValueBetween(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
		return service.readByValueBetween(min, max);
	}

	@GetMapping("/artefacts/valueLess")
	List<Artifacty> searchByValueLess(@RequestParam BigDecimal value) {
		return service.readByValueLessThan(value);
	}

	@GetMapping("/artefacts/valueGreater")
	List<Artifacty> searchByValueGreater(@RequestParam BigDecimal value) {
		return service.readByValueGreaterThan(value);
	}
	
	public void BaseDatosTeste() {
		Storart a = new Storart(1l, "BCN WhitartGalery", 50);
		Storart b = new Storart(2l, "BCN WhitartGalery", 40);
		Storart c = new Storart(3l, "BCN WhitartGalery", 10);
		Storart d = new Storart(4l, "BCN WhitartGalery", 30);
		service.saveOne(new Artifacty(1l, "Leonardo da Vinci", "Mona Lisa", new BigDecimal(123456.78), a));
		service.saveOne(new Artifacty(2l, "Michelangelo Buonarotti", "A Criação de Adão", new BigDecimal(23456.78), a));
		service.saveOne(new Artifacty(3l, "Caravaggio", "Medusa", new BigDecimal(1234.34), a));
		service.saveOne(new Artifacty(4l, "Diego Velázquez", "As Meninas", new BigDecimal(76154.43), a));
		service.saveOne(new Artifacty(5l, "Rembrandt", "A Ronda Noturna", new BigDecimal(543211.23), a));
		service.saveOne(new Artifacty(6l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(7l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(8l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(9l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(10l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(11l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(12l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(13l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(14l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(15l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(16l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(17l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(18l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(19l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(20l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(21l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(22l, "", "", new BigDecimal(10000), a));
		service.saveOne(new Artifacty(23l, "", "", new BigDecimal(10000), a));
	}

}
