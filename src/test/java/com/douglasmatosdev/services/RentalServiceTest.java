package com.douglasmatosdev.services;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;
import com.douglasmatosdev.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class RentalServiceTest {
    @Test
    public void test() {
        // cenário
        RentalService service = new RentalService();
        User user = new User("Usuário 1");
        Movie movie = new Movie("Filme 1", 2, 5.0);

        // ação
        Rental rental = service.alugarFilme(user, movie);

        // verificação
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(5.0));
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(CoreMatchers.not(6.0)));
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        Assert.assertEquals(5.0, rental.getPrice(), 0.01);

        Assert.assertTrue(DateUtils.isSameDate(rental.getDateRental(), new Date()));
        Assert.assertThat(DateUtils.isSameDate(rental.getDateRental(), new Date()), CoreMatchers.is(true));

        Assert.assertTrue(DateUtils.isSameDate(rental.getDateReturn(), DateUtils.getDateWithDiffDays(1)));
        Assert.assertThat(DateUtils.isSameDate(rental.getDateReturn(), DateUtils.getDateWithDiffDays(1)), CoreMatchers.is(true));
    }
}
