package com.api.stockgalary.dto;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "store")
public class Storart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "capacity")
	private Integer capacity;

	@OneToMany
	@JoinColumn(name = "id")
	private List<Artifacty> inventory;

	public Storart() {
	}

	public Storart(Long id, String name, Integer capacity) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Artifacty")
	public List<Artifacty> getInventory() {
		return inventory;
	}

	public void setInventory(List<Artifacty> inventory) {
		this.inventory = inventory;
	}

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
