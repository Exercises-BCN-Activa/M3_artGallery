package com.api.stockgalary.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.stockgalary.dto.*;

@RestController
@RequestMapping("/api")
public final class ApiController {
	
	ApiController() {}
	
	private final StorartController shop = new StorartController();
	private final ArtifactyController art = new ArtifactyController();
	
	@PostMapping("/galleries")
	public Storart saveOne(@RequestBody Storart storart) {
		return shop.keep(storart);
	}

	@GetMapping("/galleries")
	public List<Storart> readAll() {
		return shop.find();
	}

	@GetMapping("/galleries/id/{id}")
	public Storart readOneByPath(@PathVariable(name = "id") Long id) {
		return shop.find(id);
	}

	@GetMapping("/galleries/id")
	public Storart readOneByParam(@Param("q") Long id) {
		return shop.find(id);
	}

	@PutMapping("/galleries/id/{id}")
	public Storart updateOneByPath(@PathVariable(name = "id") Long id, @RequestBody Storart storart) {
		return shop.update(id, storart);
	}
	
	@PutMapping("/galleries")
	public Storart updateOneByParam(@RequestParam Long id, @RequestBody Storart storart) {
		return shop.update(id, storart);
	}

	@DeleteMapping("/galleries/id/{id}")
	public void deleteOneByPath(@PathVariable(name = "id") Long id) {
		shop.delete(id);
	}
	
	@DeleteMapping("/galleries")
	public void deleteOneByParam(@RequestParam Long id) {
		shop.delete(id);
	}

	@DeleteMapping("/galleries/run/shop/{password}")
	public String deleteAll(@PathVariable(name = "password") String password) {
		return shop.ERASE(password);
	}

	@GetMapping("/galleries/name/{name}")
	public List<Storart> readByNameByPath(@PathVariable(name = "name") String name) {
		return shop.searchByName(name);
	}
	
	@GetMapping("/galleries/name")
	public List<Storart> readByNameByParam(@Param("q") String name) {
		return shop.searchByName(name);
	}

	@GetMapping("/galleries/Capacity:{min}^{max}")
	public List<Storart> readByCapacityBetweenByPath(@PathVariable(name = "min") Integer min, @PathVariable(name = "max") Integer max) {
		return shop.searchByCapacityBetween(min, max);
	}
	
	@GetMapping("/galleries/Capacity")
	public List<Storart> readByCapacityBetweenByParam(@RequestParam Integer min, @RequestParam Integer max) {
		return shop.searchByCapacityBetween(min, max);
	}

	@GetMapping("/galleries/LessThan/{capacity}")
	public List<Storart> readByCapacityLessThanByPath(@PathVariable(name = "capacity") Integer capacity) {
		return shop.searchByCapacityLess(capacity);
	}
	
	@GetMapping("/galleries/LessThan")
	public List<Storart> readByCapacityLessThanByParam(@RequestParam Integer capacity) {
		return shop.searchByCapacityLess(capacity);
	}

	@GetMapping("/galleries/GreaterThan/{capacity}")
	public List<Storart> readByCapacityGreaterThanByPath(@PathVariable(name = "capacity") Integer capacity) {
		return shop.searchByCapacityGreater(capacity);
	}
	
	@GetMapping("/galleries/GreaterThan")
	public List<Storart> readByCapacityGreaterThanByParam(@RequestParam Integer capacity) {
		return shop.searchByCapacityGreater(capacity);
	}

	@GetMapping("/galleries/WhithAuthor/{author}")
	public List<Storart> readByArtifactyAuthorByPath(@PathVariable(name = "author") String author) {
		return shop.searchByArtist(author);
	}
	
	@GetMapping("/galleries/WhithAuthor")
	public List<Storart> readByArtifactyAuthorByParam(@Param("q") String author) {
		return shop.searchByArtist(author);
	}

	@GetMapping("/galleries/WhithTitle/{title}")
	public List<Storart> readByArtifactyTitleByPath(@PathVariable(name = "title") String title) {
		return shop.searchByTitle(title);
	}
	
	@GetMapping("/galleries/WhithTitle")
	public List<Storart> readByArtifactyTitleByParam(@Param("q") String title) {
		return shop.searchByTitle(title);
	}
	
// =============================== ART =============================== //
	
	@PostMapping("/galleries/artefacts")
	public Artifacty saveOneArt(@RequestParam Storart id, @RequestBody Artifacty artifacty) {
		artifacty.setShop(id);
		return art.keep(artifacty);
	}

	@GetMapping("/galleries/artefacts/all")
	public List<Artifacty> readAllArt() {
		return art.find();
	}

	@GetMapping("/galleries/artefacts/id")
	public Artifacty readOneArt(@Param("q") Long id) {
		return art.find(id);
	}

	@PutMapping("/galleries/artefacts/id")
	public Artifacty updateOneArt(@RequestParam Long id, @RequestBody Artifacty artifacty, @RequestParam Storart shopId) {
		artifacty.setShop(shopId);
		return art.update(id, artifacty);
	}

	@DeleteMapping("/galleries/artefacts/id")
	public void deleteOneArt(@RequestParam Long id) {
		art.delete(id);
	}

	@DeleteMapping("/galleries/run/art/{password}")
	public String deleteAllArt(@PathVariable(name = "password") String password) {
		return art.ERASE(password);
	}
	
	@GetMapping("/galleries/artefacts/author")
	public List<Artifacty> readByAuthor(@Param("q") String author) {
		return art.searchByAuthor(author);
	}
	
	@GetMapping("/galleries/artefacts/title")
	public List<Artifacty> readByTitle(@Param("q") String title) {
		return art.searchByTitle(title);
	}
	
	@GetMapping("/galleries/artefacts/shop")
	public List<Artifacty> readByShopName(@RequestParam String name) {
		return art.searchByShopName(name);
	}
	
	@GetMapping("/galleries/artefacts/shopId")
	public List<Artifacty> readByShopId(@RequestParam Long id) {
		return art.searchByShopId(id);
	}
	
	@GetMapping("/galleries/artefacts/valueBetween")
	public List<Artifacty> readByValueBetween(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
		return art.searchByValueBetween(min, max);
	}
	
	@GetMapping("/galleries/artefacts/valueLess")
	public List<Artifacty> readByValueLessThan(@RequestParam BigDecimal value) {
		return art.searchByValueLess(value);
	}
	
	@GetMapping("/galleries/artefacts/valueGreater")
	public List<Artifacty> readByValueGreaterThan(@RequestParam BigDecimal value) {
		return art.searchByValueGreater(value);
	}

}
