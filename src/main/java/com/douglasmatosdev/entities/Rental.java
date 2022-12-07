package com.douglasmatosdev.entities;

import java.util.Date;
import java.util.List;

public class Rental {

	private User user;
	private List<Movie> movies;
	private Date dateRental;
	private Date dateReturn;
	private Double price;

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}