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
import com.api.stockgalary.util.*;

@RestController
@RequestMapping("/api")
final class ArtifactyController {

	ArtifactyController() {
	}

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
	Artifacty update(@RequestParam Long id, @RequestParam(required = false) Storart shopId,
			@RequestParam(required = false) String author, @RequestParam(required = false) String title,
			@RequestParam(required = false) BigDecimal value) {
		Artifacty toUpdate = service.readOne(id);
		boolean ok = false;
		if (toUpdate != null) {
			if (author != null && !author.isEmpty() && !author.equalsIgnoreCase(toUpdate.getAuthor())) {
				toUpdate.setAuthor(Inputs.toTitleCase(author));
				toUpdate.setUpdate();
				ok = true;
			}
			if (title != null && !title.isEmpty() && !title.equalsIgnoreCase(toUpdate.getTitle())) {
				toUpdate.setTitle(Inputs.toTitleCase(title));
				toUpdate.setUpdate();
				ok = true;
			}
			if (value != null && value != toUpdate.getValue()) {
				toUpdate.setValue(value);
				toUpdate.setUpdate();
				ok = true;
			}
			if (shopId != null && !shopId.equals(toUpdate.getShop())) {
				toUpdate.setShop(shopId);
				toUpdate.setUpdate();
				ok = true;
			}
		}

		return (toUpdate != null && ok) ? service.updateOne(toUpdate) : null;
	}

	@DeleteMapping("/artefacts")
	void delete(@RequestParam Long id) {
		service.deleteOne(id);
	}

	@SuppressWarnings("serial")
	@GetMapping("/artefacts/search")
	List<Artifacty> search(@RequestParam String by, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long id, @RequestParam(required = false) BigDecimal min,
			@RequestParam(required = false) BigDecimal max) {

		switch (by) {

		case "id":
			return (id == null) ? null : new ArrayList<Artifacty>() {
				{
					add(service.readOne(id));
				}
			};

		case "author":
			return name == null ? null : service.readByAuthor(Inputs.toTitleCase(name));

		case "title":
			return name == null ? null : service.readByTitle(Inputs.toTitleCase(name));

		case "shop":
			if (id == null && name == null) {
				return null;
			} else if (id != null) {
				return service.readByShopId(id);
			} else if (name != null) {
				return service.readByShopName(Inputs.toTitleCase(name));
			}

		case "value":
			if (min == null && max == null) {
				return null;
			} else if (min != null && max != null) {
				return service.readByValueBetween(min, max);
			} else if (min != null && max == null) {
				return service.readByValueGreaterThan(min);
			} else if (min == null && max != null) {
				return service.readByValueLessThan(max);
			}

		default:
			return null;
		}
	}

}
