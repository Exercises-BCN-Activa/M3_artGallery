package com.api.stockgalary.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.stockgalary.dao.IArtifacty;
import com.api.stockgalary.dto.Artifacty;

/**
 * public final class of implementation service that offers access to Artifacty
 * DAO class methods, implements the CRUD method interface and invokes DAO class
 * via auto wired notation.
 * 
 * @author FaunoGuazina
 *
 */
@Service
public final class ArtifactyService implements IService<Artifacty> {

	/**
	 * standard empty constructors with access only by package
	 */
	ArtifactyService() {
	}

// DAO CLASS variable

	@Autowired
	private IArtifacty dao;

// Methods CRUD

	@Override
	public Artifacty saveOne(Artifacty artifacty) {
		return dao.save(artifacty);
	}

	@Override
	public List<Artifacty> readAll() {
		return dao.findAll();
	}

	@Override
	public Artifacty readOne(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public Artifacty updateOne(Artifacty artifacty) {
		return dao.save(artifacty);
	}

	@Override
	public void deleteOne(Long id) {
		dao.deleteById(id);
	}

// Methods by DAO class

	public String deleteAllAndEverything(Long shopId, String password) {
		boolean erase = PASSWORD.equals(password);
		if (erase) {
			dao.findByShopId(shopId).forEach(x -> dao.deleteById(x.getId()));
		}
		return (erase && dao.findByShopId(shopId).isEmpty()) 
				? "Opps! There was never anything here."
				: "I'm really sorry, but I think you got it wrong";
	}

	public List<Artifacty> readByAuthor(String name) {
		return dao.findByAuthorContains(name);
	}

	public List<Artifacty> readByTitle(String title) {
		return dao.findByTitleContains(title);
	}

	public List<Artifacty> readByShopName(String name) {
		return dao.findByShopNameContains(name);
	}

	public List<Artifacty> readByShopId(Long id) {
		return dao.findByShopId(id);
	}

	public List<Artifacty> readByValueBetween(BigDecimal min, BigDecimal max) {
		return dao.findByValueBetween(min, max);
	}

	public List<Artifacty> readByValueLessThan(BigDecimal value) {
		return dao.findByValueLessThan(value);
	}

	public List<Artifacty> readByValueGreaterThan(BigDecimal value) {
		return dao.findByValueGreaterThan(value);
	}

	/**
	 * password to delete everything, private and final attribute
	 */
	private final String PASSWORD = "Incendiar Quadres";

}
