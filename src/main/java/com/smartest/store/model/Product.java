package com.smartest.store.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartest.store.utils.DateUtils;

/**
 * 
 * Entity that represents a product in the database
 * 
 * @author Tiago Santos
 * @since   2020-08-21
 * 
 */
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonIgnore
	private Integer id;
	@NotEmpty
	private String title;
	@NotEmpty
	private String type;
	private String description;
	private String filename;
	private long height;
	private long width;
	private double price;
	private int rating;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Calendar createdAt;
	
	
	
	/**
	 * @deprecated
	 */
	public Product() {

	}
	
	public Product(Integer id) {
		this.id = id;
	}
	
	public Product(String title, String type, double price) {
		this.title = title;
		this.type = type;
		this.price = price;
	}
	
	

	public Product(String title, String type, String description, String filename, long height,
			long width, double price, int rating) {
		this.title = title;
		this.type = type;
		this.description = description;
		this.filename = filename;
		this.height = height;
		this.width = width;
		this.price = price;
		this.rating = rating;
		this.createdAt = Calendar.getInstance();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", type=" + type + ", description=" + description
				+ ", filename=" + filename + ", height=" + height + ", width=" + width + ", price=" + price
				+ ", rating=" + rating + ", createdAt=" + DateUtils.getInstance().dateToString(createdAt, null) + "]";
	}

}
