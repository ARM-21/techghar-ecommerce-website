package com.techghar.model;

import javax.servlet.http.Part;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private int stock;
    private String imageURL;
    private int rating;
    private int brand;
    private String brandName;
    private String categoryName;
    private int category;
    private String createdAt;
    private Part imageBinaryContent;
    
    public Part getImageBinaryContent() {
		return imageBinaryContent;
	}
	public void setImageBinaryContent(Part imageBinaryContent) {
		this.imageBinaryContent = imageBinaryContent;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
    
    public Product()
    {
    	
    }
    public Product(int id, String name, double price, String description, int stock, String imageURL, int rating,
			int brand, int category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.imageURL = imageURL;
		this.rating = rating;
		this.brand = brand;
		this.category = category;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getBrand() {
		return brand;
	}
	public void setBrand(int brand) {
		this.brand = brand;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    // Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;

	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


}
