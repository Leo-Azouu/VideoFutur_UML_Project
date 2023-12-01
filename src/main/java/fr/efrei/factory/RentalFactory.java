package fr.efrei.factory;

import fr.efrei.util.Helper;
import java.util.Date;
import fr.efrei.domain.Rental;

public class RentalFactory {

    public static Rental createRental(int rentalId, Date rentalDate, Date returnDate, Movie rentedMovie){

        if (Helper.isNumericEmpty(rentalId) || Helper.isDateValid(rentalDate) || Helper.isDateValid(returnDate)){
            return null;
        }

        return new Rental.Builder().setRentalId(rentalId).setRentalDate(rentalDate).setReturnDate(returnDate).setRentedMovie(rentedMovie).build();

    }

}
