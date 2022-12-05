package com.douglasmatosdev.services;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;
import com.douglasmatosdev.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

public class RentalServiceTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testRental() throws Exception {
        // cenário
        RentalService service = new RentalService();
        User user = new User("Usuário 1");
        Movie movie = new Movie("Filme 1", 2, 5.0);

        // ação
        Rental rental = service.rentalMovie(user, movie);

        // verificação
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(5.0));
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(CoreMatchers.not(6.0)));
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        Assert.assertEquals(5.0, rental.getPrice(), 0.01);

        Assert.assertTrue(DateUtils.isSameDate(rental.getDateRental(), new Date()));
        Assert.assertThat(DateUtils.isSameDate(rental.getDateRental(), new Date()), CoreMatchers.is(true));

        Assert.assertTrue(DateUtils.isSameDate(rental.getDateReturn(), DateUtils.getDateWithDiffDays(1)));
        Assert.assertThat(DateUtils.isSameDate(rental.getDateReturn(), DateUtils.getDateWithDiffDays(1)), CoreMatchers.is(true));

        // Using ErrorCollector
        errorCollector.checkThat(rental.getPrice(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getDateRental(), new Date()), CoreMatchers.is(true));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getDateReturn(), DateUtils.getDateWithDiffDays(1)), CoreMatchers.is(true));

    }

    @Test(expected = Exception.class)
    public void testRental_movieWithoutStock() throws Exception {
        // cenário
        RentalService service = new RentalService();
        User user = new User("Usuário 1");
        Movie movie = new Movie("Filme 1", 0, 5.0);

        // ação
        service.rentalMovie(user, movie);
    }

    @Test
    public void testRental_movieWithoutStock2() {
        // cenário
        RentalService service = new RentalService();
        User user = new User("Usuário 1");
        Movie movie = new Movie("Filme 1", 0, 5.0);

        // ação
        try {
            service.rentalMovie(user, movie);
            Assert.fail("Should be throw exception");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Not has this movie in stock"));
        }
    }

    @Test
    public void testRental_movieWithoutStock3() throws Exception {
        // cenário
        RentalService service = new RentalService();
        User user = new User("Usuário 1");
        Movie movie = new Movie("Filme 1", 0, 5.0);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Not has this movie in stock");

        // ação
        service.rentalMovie(user, movie);
    }
}
