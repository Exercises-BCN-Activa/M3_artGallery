package com.api.stockgalary.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.stockgalary.dto.Storart;

public interface IStorart extends JpaRepository<Storart, Long> {

	public List<Storart> findByNameContains(String name);

	public List<Storart> findByCapacityBetween(Integer min, Integer max);
	
	public List<Storart> findByCapacityLessThan(Integer capacity);
	
	public List<Storart> findByCapacityGreaterThan(Integer capacity);
	
	public List<Storart> findDistinctByInventoryAuthorContains(String author);
	
	public List<Storart> findDistinctByInventoryTitleContains(String title);
	
}
