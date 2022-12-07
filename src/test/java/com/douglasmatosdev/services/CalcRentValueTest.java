package com.douglasmatosdev.services;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalcRentValueTest {
    private RentalService service;
    @Parameterized.Parameter
    public List<Movie> movies;
    @Parameterized.Parameter(value = 1)
    public Double rentValue;

    @Parameterized.Parameter(value = 2)
    public String scenario;

    @Before
    public void before() {
        service = new RentalService();
    }

    private static Movie movie1 = new Movie("Filme 1", 2, 4.0);
    private static Movie movie2 = new Movie("Filme 2", 2, 4.0);
    private static Movie movie3 = new Movie("Filme 3", 2, 4.0);
    private static Movie movie4 = new Movie("Filme 4", 2, 4.0);
    private static Movie movie5 = new Movie("Filme 5", 2, 4.0);
    private static Movie movie6 = new Movie("Filme 6", 2, 4.0);
    private static Movie movie7 = new Movie("Filme 7", 2, 4.0);


    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(movie1, movie2), 8.0, "2 Movies: Without Discount"},
                {Arrays.asList(movie1, movie2, movie3), 11.0, "3 Movies: 25% Discount"},
                {Arrays.asList(movie1, movie2, movie3, movie4), 13.0, "4 Movies: 50% Discount"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5), 14.0, "5 Movies: 75% Discount"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6), 14.0, "6 Movies: 100% Discount"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7), 18.0, "7 Movies: Without Discount"}
        });
    }

    @Test
    public void shouldCalcRentValueAndApplyDiscount() throws Exception {
        // scenario
        User user = new User("Usuário 1");

        // action
        Rental rental = service.rentalMovies(user, movies);

        // verification
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(rentValue));
    }
}
