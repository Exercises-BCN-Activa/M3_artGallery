package com.api.stockgalary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.stockgalary.dao.IStorart;
import com.api.stockgalary.dto.Storart;

/**
 * public final class of implementation service that offers access to Storart
 * DAO class methods, implements the CRUD method interface and invokes DAO class
 * via auto wired notation.
 * 
 * @author FaunoGuazina
 *
 */
@Service
public final class StorartService implements IService<Storart> {

	/**
	 * standard empty constructors with access only by package
	 */
	StorartService() {
	}

// DAO CLASS variable

	@Autowired
	private IStorart dao;

// Methods CRUD

	@Override
	public Storart saveOne(Storart storart) {
		return dao.save(storart);
	}

	@Override
	public List<Storart> readAll() {
		return dao.findAll();
	}

	@Override
	public Storart readOne(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public Storart updateOne(Storart storart) {
		return dao.save(storart);
	}

	@Override
	public void deleteOne(Long id) {
		dao.deleteById(id);
	}

// Methods by DAO class

	public String deleteAllAndEverything(String password) {
		boolean erase = PASSWORD.equals(password);
		if (erase) {
			dao.deleteAll();
		}
		return erase 
				? "Opps! There was never anything here." 
				: "I'm really sorry, but I think you got it wrong";
	}

	public List<Storart> readByName(String name) {
		return dao.findByNameContains(name);
	}

	public List<Storart> readByCapacityBetween(Integer min, Integer max) {
		return dao.findByCapacityBetween(min, max);
	}

	public List<Storart> readByCapacityLessThan(Integer capacity) {
		return dao.findByCapacityLessThan(capacity);
	}

	public List<Storart> readByCapacityGreaterThan(Integer capacity) {
		return dao.findByCapacityGreaterThan(capacity);
	}

	public List<Storart> readByArtifactyAuthor(String author) {
		return dao.findDistinctByInventoryAuthorContains(author);
	}

	public List<Storart> readByArtifactyTitle(String title) {
		return dao.findDistinctByInventoryTitleContains(title);
	}

	/**
	 * password to delete everything, private and final attribute
	 */
	private final String PASSWORD = "Sense Deixar Rastre";
}
