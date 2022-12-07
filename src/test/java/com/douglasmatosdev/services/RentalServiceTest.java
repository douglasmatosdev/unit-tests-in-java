package com.douglasmatosdev.services;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;
import com.douglasmatosdev.exceptions.MovieWithoutStockException;
import com.douglasmatosdev.exceptions.RentalCompanyException;
import com.douglasmatosdev.matchers.CustomMatchers;
import com.douglasmatosdev.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
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
    public void shouldRentalMovie() throws Exception {
        /**
         * Este teste só funciona aos sábados
         */
        Assume.assumeFalse(DateUtils.verifyDayWeek(new Date(), Calendar.SATURDAY));

        // scenario
        User user = new User("Usuário 1");
        List<Movie> movies = Arrays.asList(
                new Movie("Filme 1", 4, 5.0),
                new Movie("Filme 2", 5, 5.0)
        );

        // action
        Rental rental = service.rentalMovies(user, movies);

        // verification
        errorCollector.checkThat(rental.getPrice(), CoreMatchers.is(CoreMatchers.equalTo(10.0)));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getDateRental(), new Date()), CoreMatchers.is(true));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getDateReturn(), DateUtils.getDateWithDiffDays(1)), CoreMatchers.is(true));
    }

    @Test(expected = MovieWithoutStockException.class)
    public void shouldThrowExceptionWhenTryRentMovieWithoutStock() throws Exception {
        // scenario
        User user = new User("Usuário 1");
        List<Movie> movies = Arrays.asList(
                new Movie("Filme 1", 0, 5.0),
                new Movie("Filme 2", 0, 5.0),
                new Movie("Filme 3", 0, 5.0)
        );

        // action
        service.rentalMovies(user, movies);
    }

    @Test
    public void shouldThrowExceptionWhenTryRentMovieWithoutUser() throws MovieWithoutStockException {
        // scenario
        List<Movie> movies = Arrays.asList(
                new Movie("Filme 1", 4, 5.0),
                new Movie("Filme 2", 5, 5.0),
                new Movie("Filme 3", 6, 5.0)
        );

        // action
        try {
            service.rentalMovies(null, movies);
            Assert.fail();
        } catch (RentalCompanyException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Empty User"));
        }
    }

    @Test
    public void shouldThrowExceptionWhenTryRentMovieWithoutMovie() throws MovieWithoutStockException, RentalCompanyException {
        // scenario
        User user = new User("Usuário 1");

        expectedException.expect(RentalCompanyException.class);
        expectedException.expectMessage("Empty Movie");

        // action
        service.rentalMovies(user, null);
    }

    @Test
    public void shouldDeliveryInSundayIfRentOnSaturday() throws MovieWithoutStockException, RentalCompanyException {
        /**
         * Este teste só funciona aos sábados
         */
        Assume.assumeTrue(DateUtils.verifyDayWeek(new Date(), Calendar.SATURDAY));

        // scenario
        User user = new User("User 1");
        List<Movie> movies = Arrays.asList(new Movie("Filme 1", 1, 5.0));

        // action
        Rental rental = service.rentalMovies(user, movies);

        // verification
        // Assert.assertThat(rental.getDateReturn(), new DayWeekMatcher(Calendar.MONDAY));
        // Assert.assertThat(rental.getDateReturn(), CustomMatchers.willBe(Calendar.MONDAY));
        // Assert.assertThat(rental.getDateReturn(), CustomMatchers.willBe(Calendar.SUNDAY));
        Assert.assertThat(rental.getDateReturn(), CustomMatchers.willBeMonday());
    }

}
