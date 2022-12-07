package com.douglasmatosdev.builders;

import com.douglasmatosdev.entities.Movie;

public class MovieBuilder {
    private Movie movie;

    private MovieBuilder() {
    }

    public static MovieBuilder oneMovie() {
        MovieBuilder movieBuilder = new MovieBuilder();
        movieBuilder.movie = new Movie();
        movieBuilder.movie.setName("Movie 1");
        movieBuilder.movie.setStock(2);
        movieBuilder.movie.setPriceRental(4.0);
        return movieBuilder;
    }

    public static MovieBuilder oneMovieWithoutStock() {
        MovieBuilder movieBuilder = new MovieBuilder();
        movieBuilder.movie = new Movie();
        movieBuilder.movie.setName("Movie 1");
        movieBuilder.movie.setStock(0);
        movieBuilder.movie.setPriceRental(4.0);
        return movieBuilder;
    }

    public MovieBuilder withoutStock() {
        movie.setStock(0);
        return this;
    }

    public MovieBuilder withValue(Double value) {
        movie.setPriceRental(value);
        return this;
    }

    public Movie build() {
        return movie;
    }
}
