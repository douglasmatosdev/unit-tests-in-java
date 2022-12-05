package com.douglasmatosdev.entities;

public class Movie {

	private String name;
	private Integer stock;
	private Double priceRental;
	
	public Movie() {}
	
	public Movie(String name, Integer stock, Double priceRental) {
		this.name = name;
		this.stock = stock;
		this.priceRental = priceRental;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getPriceRental() {
		return priceRental;
	}
	public void setPriceRental(Double priceRental) {
		this.priceRental = priceRental;
	}
}