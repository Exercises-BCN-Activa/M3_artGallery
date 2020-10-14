package com.api.stockgalary.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.stockgalary.dto.Artifacty;

/**
 * DAO class that provides access to the art pictures/products entity and
 * extends JpaRepository.
 * 
 * @author FaunoGuazina
 */
public interface IArtifacty extends JpaRepository<Artifacty, Long> {

	/**
	 * find any product that string painter name parameter contains
	 * 
	 * @param author string of painter name
	 * @return List<Artifacty>
	 */
	public List<Artifacty> findByAuthorContains(String author);

	/**
	 * find any product that string picture title parameter contains
	 * 
	 * @param title string of picture/product name
	 * @return List<Artifacty>
	 */
	public List<Artifacty> findByTitleContains(String title);

	/**
	 * find any product that string shop name parameter contains
	 * 
	 * @param name string of shop name
	 * @return List<Artifacty>
	 */
	public List<Artifacty> findByShopNameContains(String name);

	/**
	 * find any product that long number shop id parameter contains
	 * 
	 * @param id long number value
	 * @return List<Artifacty>
	 */
	public List<Artifacty> findByShopId(Long id);

	/**
	 * find any product that value/price between minimum and maximum parameters
	 * 
	 * @param min BigDecimal number value
	 * @param max BigDecimal number value
	 * @return List<Artifacty>
	 */
	public List<Artifacty> findByValueBetween(BigDecimal min, BigDecimal max);

	/**
	 * find any product that value/price less than maximum value parameters
	 * 
	 * @param value BigDecimal number value
	 * @return List<Artifacty>
	 */
	public List<Artifacty> findByValueLessThan(BigDecimal value);

	/**
	 * find any product that value/price greater than minimum value parameters
	 * 
	 * @param value BigDecimal number value
	 * @return List<Artifacty>
	 */
	public List<Artifacty> findByValueGreaterThan(BigDecimal value);

}
