package com.smartest.store.controller.form;

import javax.validation.constraints.NotEmpty;

import com.smartest.store.model.Product;
import com.smartest.store.repository.ProductRepository;

/**
 * 
 * Form that contains product data to create/update a product in the database
 * 
 * @author Tiago Santos
 * @since   2020-08-21
 * 
 */
public class ProductForm {
	
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

	public Product update(Integer id, ProductRepository productRepository) {
		Product product = productRepository.getOne(id);
		
		product.setTitle(title);
		product.setType(type);
		product.setDescription(description);
		product.setFilename(filename);
		product.setHeight(height);
		product.setWidth(width);
		product.setPrice(price);
		product.setRating(rating);
		
		return product;
	}
	
	public Product convertToProduct() {
		return new Product(title, type, description, filename, height, width, price, rating);
	}

	@Override
	public String toString() {
		return "ProductForm [title=" + title + ", type=" + type + ", description=" + description + ", filename="
				+ filename + ", height=" + height + ", width=" + width + ", price=" + price + ", rating=" + rating
				+ "]";
	}

}
