package com.douglasmatosdev.services;

import com.douglasmatosdev.entities.Movie;
import com.douglasmatosdev.utils.DateUtils;
import com.douglasmatosdev.entities.Rental;
import com.douglasmatosdev.entities.User;

import java.util.Date;

public class RentalService {

    public Rental rentalMovie(User user, Movie movie) throws Exception {
        if(movie.getStock() == 0) {
            throw new Exception("Not has this movie in stock");
        }
        Rental rental = new Rental();
        rental.setFilme(movie);
        rental.setUsuario(user);
        rental.setDateRental(new Date());
        rental.setPrice(movie.getPriceRental());

        //Entrega no dia seguinte
        Date daliveryDate = new Date();
        daliveryDate = DateUtils.addDays(daliveryDate, 1);
        rental.setDateReturn(daliveryDate);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return rental;
    }


}