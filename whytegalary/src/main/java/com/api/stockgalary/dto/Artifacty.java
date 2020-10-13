package com.api.stockgalary.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

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

@Entity
@Table(name = "product")
public class Artifacty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "creator")
	private String author = "unknown";

	@Column(name = "name")
	private String title;

	@Column(name = "price", scale = 2)
	private BigDecimal value;

	@Column(name = "date", updatable = false)
	private final Calendar registration = Calendar.getInstance();;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Calendar updateTime;

	@Column(name = "update_score")
	private Integer updateScore = 0;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Storart shop;

	public Artifacty() {
	}

	public Artifacty(Long id, String author, String title, Double value, Storart shop) {
		this.id = id;
		setAuthor(author);
		this.title = title;
		this.value = new BigDecimal(value);
	}

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = (author == null || author.isEmpty()) ? "unknown" : author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setUpdate() {
		updateTime = Calendar.getInstance();
		updateScore++;
	}

	public void setShop(Storart shop) {
		this.shop = shop;
	}

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
