package com.douglasmatosdev.services;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.exceptions.MovieWithoutStockException;
import com.douglasmatosdev.exceptions.RentalCompanyException;
import com.douglasmatosdev.utils.DateUtils;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;

import java.util.Date;
import java.util.List;

public class RentalService {

    public Rental rentalMovies(User user, List<Movie> movies) throws RentalCompanyException, MovieWithoutStockException {
        if (user == null) {
            throw new RentalCompanyException("Empty User");
        }

        if (movies == null || movies.isEmpty()) {
            throw new RentalCompanyException("Empty Movie");
        }

        for (Movie movie : movies) {
            if (movie.getStock() == 0) {
                throw new MovieWithoutStockException();
            }
        }

        Rental rental = new Rental();
        rental.setMovies(movies);
        rental.setUsuario(user);
        rental.setDateRental(new Date());
        Double totalPrice = 0d;
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            Double price = movie.getPriceRental();
            switch (i) {
                case 2:
                    price = price * 0.75;
                    break;
                case 3:
                    price = price * 0.5;
                    break;
                case 4:
                    price = price * 0.25;
                    break;
                case 5:
                    price = 0d;
                    break;
            }

            totalPrice += price;
        }
        rental.setPrice(totalPrice);

        //Entrega no dia seguinte
        Date daliveryDate = new Date();
        daliveryDate = DateUtils.addDays(daliveryDate, 1);
        rental.setDateReturn(daliveryDate);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return rental;
    }
}