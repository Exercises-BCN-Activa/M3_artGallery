package com.api.stockgalary.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DTO class that mimics the art pictures/products entity
 * 
 * @author FaunoGuazina
 */
@Entity
@Table(name = "product")
public class Artifacty {

//INDEX METHODS -> Attributes - Constructors - Getters - Setters - Hash/equals/toString

//Attributes and Notations 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "creator")
	private String author = "unknown";

	@Column(name = "name")
	private String title;

	@Column(name = "price", scale = 2)
	private BigDecimal value;

	/**
	 * defined as final so as not to suffer alterations
	 */
	@Column(name = "date", updatable = false)
	private final Calendar registration = Calendar.getInstance();;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Calendar updateTime;

	@Column(name = "update_score")
	private Integer updateScore = 0;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Storart.class)
	@JoinColumn(name = "store_id")
	private Storart shop;

	/**
	 * empty standard constructor
	 */
	public Artifacty() {
	}

	/**
	 * Constructor with the minimum parameters necessary to build an object of this
	 * DTO entity
	 * 
	 * @param id     if null the MySQL server will be provide an auto increment one.
	 * @param author string name of painter
	 * @param title  string name of picture
	 * @param value  double will be converted an BigDecimal format
	 * @param shop   object from Storart Class
	 */
	public Artifacty(Long id, String author, String title, Double value, Storart shop) {
		this.id = id;
		setAuthor(author);
		this.title = title;
		this.value = new BigDecimal(value);
	}

// GETTERS =========>

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Calendar getRegistration() {
		return registration;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public Integer getUpdateScore() {
		return updateScore;
	}

	public Storart getShop() {
		return shop;
	}

// SETTERS =========>

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param author if null or empty the "unknown" will be set.
	 */
	public void setAuthor(String author) {
		this.author = (author == null || author.isEmpty()) ? "unknown" : author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * increases the count of the number of times the object has been updated, as
	 * well as setting a date for the updateTime attribute
	 */
	public void setUpdate() {
		updateTime = Calendar.getInstance();
		updateScore++;
	}

	public void setShop(Storart shop) {
		this.shop = shop;
	}

// HASH - EQUALS - toSTRING =========>

	@Override
	public int hashCode() {
		return Objects.hash(author, id, registration, shop, title, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artifacty other = (Artifacty) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(registration, other.registration) && Objects.equals(shop, other.shop)
				&& Objects.equals(title, other.title) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "Artifacty [id=" + id + ", author=" + author + ", title=" + title + ", value=" + value
				+ ", registration=" + registration + ", shop=" + shop + "]";
	}

}
