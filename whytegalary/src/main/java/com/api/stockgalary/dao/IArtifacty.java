package com.api.stockgalary.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.stockgalary.dto.Artifacty;

public interface IArtifacty extends JpaRepository<Artifacty, Long> {
	
	public List<Artifacty> findByAuthorContains(String author);

	public List<Artifacty> findByTitleContains(String title);
	
	public List<Artifacty> findByShopNameContains(String name);
	
	public List<Artifacty> findByShopId(Long id);
	
	public List<Artifacty> findByValueBetween(BigDecimal min, BigDecimal max);
	
	public List<Artifacty> findByValueLessThan(BigDecimal value);
	
	public List<Artifacty> findByValueGreaterThan(BigDecimal value);
	
}
