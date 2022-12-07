package com.douglasmatosdev.services;

import buildermaster.BuilderMaster;
import com.douglasmatosdev.builders.MovieBuilder;
import com.douglasmatosdev.builders.UserBuilder;
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
        User user = UserBuilder.oneUser().build();
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().withValue(5.0).build());

        // action
        Rental rental = service.rentalMovies(user, movies);

        // verification
        errorCollector.checkThat(rental.getPrice(), CoreMatchers.is(5.0));
        errorCollector.checkThat(rental.getDateRental(), CustomMatchers.isToday());
        errorCollector.checkThat(rental.getDateReturn(), CustomMatchers.isTodayDiffDays(1));
    }

    @Test(expected = MovieWithoutStockException.class)
    public void shouldThrowExceptionWhenTryRentMovieWithoutStock() throws Exception {
        // scenario
        User user = UserBuilder.oneUser().build();

        // Wih pattern Chaining method
        // List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().withoutStock().now());

        // With pattern Object model
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovieWithoutStock().build());

        // action
        service.rentalMovies(user, movies);
    }

    @Test
    public void shouldThrowExceptionWhenTryRentMovieWithoutUser() throws MovieWithoutStockException {
        // scenario
        List<Movie> movies = Arrays.asList(
                MovieBuilder.oneMovie().build(),
                MovieBuilder.oneMovie().build(),
                MovieBuilder.oneMovie().build()
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
        User user = UserBuilder.oneUser().build();

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
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().build());

        // action
        Rental rental = service.rentalMovies(user, movies);

        // verification
        Assert.assertThat(rental.getDateReturn(), CustomMatchers.willBeMonday());
    }

    public static void main(String[] args) {
        new BuilderMaster().gerarCodigoClasse(Rental.class);
    }
}
