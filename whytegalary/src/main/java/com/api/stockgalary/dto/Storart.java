package com.api.stockgalary.dto;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * DTO class that mimics the art shops entity
 * 
 * @author FaunoGuazina
 */
@Entity
@Table(name = "store")
public class Storart {

//INDEX METHODS -> Attributes - Constructors - Getters - Setters - Hash/equals/toString

//Attributes and Notations 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "capacity")
	private Integer capacity;

	/**
	 * mappedBy = "store" -> I can't do this good practice
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Artifacty.class) // mappedBy = "store"
	@JoinColumn(name = "id")
	private List<Artifacty> inventory;

	/**
	 * empty standard constructor
	 */
	public Storart() {
	}

	/**
	 * Constructor with the minimum parameters necessary to build an object of this
	 * DTO entity
	 * 
	 * @param id       if null the MySQL server will be provide an auto increment
	 *                 one.
	 * @param name     string name of painter of store
	 * @param capacity integer number of stock capacity of this store
	 */
	public Storart(Long id, String name, Integer capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

// GETTERS =========>

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	@JsonIgnore
	@JoinColumn(name = "id")
	public List<Artifacty> getInventory() {
		return inventory;
	}

// SETTERS =========>

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setInventory(List<Artifacty> inventory) {
		this.inventory = inventory;
	}

// HASH - EQUALS - toSTRING =========>

	@Override
	public int hashCode() {
		return Objects.hash(capacity, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Storart other = (Storart) obj;
		return Objects.equals(capacity, other.capacity) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Storart [id=" + id + ", name=" + name + ", capacity=" + capacity + "]";
	}

}
