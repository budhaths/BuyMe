package com.cs336.javafiles;

public class Item {
	private int item_id;
	private String category;
	private String brand ;
	private String gender;
	private float size;		
	private String model;
	private String color;
	private String seller; 
	private float minPrice; 
	private float current_price;
	private String startDate; 
	private String endDate;
	
	

	public Item(int item_id, String category, String brand, String gender, float size, String model, String color, String seller,
			float minPrice, float current_price, String startDate, String endDate) {
		this.item_id=item_id;
		this.category = category;
		this.brand = brand;
		this.gender = gender;
		this.size = size;
		this.model = model;
		this.color = color;
		this.seller = seller;
		this.minPrice = minPrice;
		this.current_price=current_price;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Item(String category, String brand, String gender, float size, String model, String color, String seller,
			float minPrice, String startDate, String endDate) {
	
		this.category = category;
		this.brand = brand;
		this.gender = gender;
		this.size = size;
		this.model = model;
		this.color = color;
		this.seller = seller;
		this.minPrice = minPrice;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Item( String category, String brand, String gender, float size, String model, String color, String endDate) {
		
		this.category=category;
		this.brand = brand;
		this.gender = gender;
		this.size = size;
		this.model = model;
		this.color = color;
		this.endDate = endDate;
	}
	
	public Item(int item_id, String category, String brand, String gender, float size, String model, String color, String endDate) {
		this.item_id=item_id;
		this.category=category;
		this.brand = brand;
		this.gender = gender;
		this.size = size;
		this.model = model;
		this.color = color;
		this.endDate = endDate;
	}
	
	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public float getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(float current_price) {
		this.current_price = current_price;
	}
}
