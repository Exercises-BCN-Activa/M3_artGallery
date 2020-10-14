package com.api.stockgalary.service;

import java.util.List;

/**
 * interface that make up a basic system of methods that meets simple CRUD
 * requirements
 * 
 * @author FaunoGuazina
 *
 * @param <T> generic class that assists in the construction of method
 *            parameters
 */
interface IService<T> {

	// ------------------------------// CRUD Methods

	public T saveOne(T item); // Save One

	public List<T> readAll(); // Read All

	public T readOne(Long id); // Read One Item

	public T updateOne(T item); // Update One Item

	public void deleteOne(Long id); // Delete One Item

}
