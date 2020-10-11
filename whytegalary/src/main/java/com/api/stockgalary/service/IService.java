package com.api.stockgalary.service;

import java.util.List;

interface IService<T> {

	// ------------------------------// CRUD Methods

	public T saveOne(T item); // Save One

	public List<T> readAll(); // Read All

	public T readOne(Long id); // Read One Item

	public T updateOne(Long id, T item); // Update One Item

	public void deleteOne(Long id); // Delete One Item

	public String deleteAllAndEverything(String password); // Delete All Items
	
	final String PASSWORD = "Sense Deixar Rastre";

}
