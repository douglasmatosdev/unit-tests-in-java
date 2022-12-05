package com.douglasmatosdev.entities;

import java.util.Date;

public class Rental {

	private User user;
	private Movie movie;
	private Date dateRental;
	private Date dateReturn;
	private Double price;
	
	public User getUsuario() {
		return user;
	}
	public void setUsuario(User user) {
		this.user = user;
	}
	public Date getDateRental() {
		return dateRental;
	}
	public void setDateRental(Date dateRental) {
		this.dateRental = dateRental;
	}
	public Date getDateReturn() {
		return dateReturn;
	}
	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Movie getFilme() {
		return movie;
	}
	public void setFilme(Movie movie) {
		this.movie = movie;
	}
}