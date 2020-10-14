package com.api.stockgalary.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.stockgalary.dto.Storart;

/**
 * DAO class that provides access to the art shops entity and extends
 * JpaRepository.
 * 
 * @author FaunoGuazina
 */
public interface IStorart extends JpaRepository<Storart, Long> {

	/**
	 * find stores that your own name contains the string parameter
	 * 
	 * @param name string name of shop
	 * @return List<Storart>
	 */
	public List<Storart> findByNameContains(String name);

	/**
	 * find stores that your own capacity between minimum and maximum integer
	 * parameter
	 * 
	 * @param min integer value
	 * @param max integer value
	 * @return List<Storart>
	 */
	public List<Storart> findByCapacityBetween(Integer min, Integer max);

	/**
	 * find stores that your own capacity less than minimum integer parameter
	 * 
	 * @param capacity integer value
	 * @return List<Storart>
	 */
	public List<Storart> findByCapacityLessThan(Integer capacity);

	/**
	 * find stores that your own capacity less than maximum integer parameter
	 * 
	 * @param capacity integer value
	 * @return List<Storart>
	 */
	public List<Storart> findByCapacityGreaterThan(Integer capacity);

	/**
	 * find distinct stores that painter name contains the string parameter
	 * 
	 * @param author string of painter name
	 * @return List<Storart>
	 */
	public List<Storart> findDistinctByInventoryAuthorContains(String author);

	/**
	 * find distinct stores that product title/name contains the string parameter
	 * 
	 * @param title string of picture/product name
	 * @return List<Storart>
	 */
	public List<Storart> findDistinctByInventoryTitleContains(String title);

}
