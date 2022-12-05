package com.douglasmatosdev.services;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;
import com.douglasmatosdev.exceptions.MovieWithoutStockException;
import com.douglasmatosdev.exceptions.RentalCompanyException;
import com.douglasmatosdev.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RentalServiceTest {

    private RentalService service;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        service = new RentalService();
    }

    @Test
    public void testRental() throws Exception {
        // cenário
        User user = new User("Usuário 1");
        List<Movie> movies = Arrays.asList(
                new Movie("Filme 1", 4, 5.0),
                new Movie("Filme 2", 5, 5.0),
                new Movie("Filme 3", 6, 5.0)
        );

        // ação
        Rental rental = service.rentalMovies(user, movies);

        // verificação
        errorCollector.checkThat(rental.getPrice(), CoreMatchers.is(CoreMatchers.equalTo(15.0)));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getDateRental(), new Date()), CoreMatchers.is(true));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getDateReturn(), DateUtils.getDateWithDiffDays(1)), CoreMatchers.is(true));
    }

    @Test(expected = MovieWithoutStockException.class)
    public void testRental_movieWithoutStock() throws Exception {
        // cenário
        User user = new User("Usuário 1");
        List<Movie> movies = Arrays.asList(
                new Movie("Filme 1", 0, 5.0),
                new Movie("Filme 2", 0, 5.0),
                new Movie("Filme 3", 0, 5.0)
        );

        // ação
        service.rentalMovies(user, movies);
    }

    /**
     * 2º forma de tratar exceptions, FORMA ROBUSTA
     */
    @Test
    public void testRental_userEmpty() throws MovieWithoutStockException {
        // cenário
        List<Movie> movies = Arrays.asList(
                new Movie("Filme 1", 4, 5.0),
                new Movie("Filme 2", 5, 5.0),
                new Movie("Filme 3", 6, 5.0)
        );

        // ação
        try {
            service.rentalMovies(null, movies);
            Assert.fail();
        } catch (RentalCompanyException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Empty User"));
        }
    }

    /**
     * 3° forma de tratar exceptions, FORMA NOVA
     *
     * @throws Exception
     */
    @Test
    public void testRental_movieEmpty() throws MovieWithoutStockException, RentalCompanyException {
        // cenário
        User user = new User("Usuário 1");

        expectedException.expect(RentalCompanyException.class);
        expectedException.expectMessage("Empty Movie");

        // ação
        service.rentalMovies(user, null);
    }
}
