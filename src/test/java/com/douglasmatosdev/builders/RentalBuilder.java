package com.douglasmatosdev.builders;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;
import com.douglasmatosdev.utils.DateUtils;

import java.util.Arrays;
import java.util.Date;


public class RentalBuilder {
    private Rental rental;
    private RentalBuilder(){}

    public static RentalBuilder oneRental() {
        RentalBuilder builder = new RentalBuilder();
        initializingDefaultData(builder);
        return builder;
    }

    public static void initializingDefaultData(RentalBuilder builder) {
        builder.rental = new Rental();
        Rental rental = builder.rental;

        rental.setUser(UserBuilder.oneUser().build());
        rental.setMovies(Arrays.asList(MovieBuilder.oneMovie().build()));
        rental.setDateRental(new Date());
        rental.setDateReturn(DateUtils.getDateWithDiffDays(1));
        rental.setPrice(4.0);
    }

    public RentalBuilder withUser(User param) {
        rental.setUser(param);
        return this;
    }

    public RentalBuilder withListMovies(Movie... params) {
        rental.setMovies(Arrays.asList(params));
        return this;
    }

    public RentalBuilder withDateRental(Date param) {
        rental.setDateRental(param);
        return this;
    }

    public RentalBuilder withDateReturn(Date param) {
        rental.setDateReturn(param);
        return this;
    }

    public RentalBuilder withPrice(Double param) {
        rental.setPrice(param);
        return this;
    }

    public Rental build() {
        return rental;
    }
}

