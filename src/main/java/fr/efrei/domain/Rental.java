package fr.efrei.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Rental {

    private int rentalId;
    private Date rentalDate;
    private Date returnDate;
    private Movie rentedMovie;

    private Rental(){}

    private Rental(Builder builder){
        this.rentalId = builder.rentalId;
        this.rentalDate = builder.rentalDate;
        this.returnDate = builder.returnDate;
        this.rentedMovie = builder.rentedMovie;
    }

    public int getRentalId() {
        return rentalId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    // just for test late date public void setRentalDate(Date rentalDate) {
    //    this.rentalDate = rentalDate;
    //}

    public Date getReturnDate() {
        return returnDate;
    }

    public Movie getRentedMovie() {
        return rentedMovie;
    }
    public void adjustReturnDate(int daysToAdd) {
        LocalDate adjustedReturnDate = LocalDate.now().plusDays(daysToAdd);
        this.returnDate = Date.from(adjustedReturnDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public double calculateRentalFee() {
        if (returnDate != null) {
            long daysRented = ChronoUnit.DAYS.between(rentalDate.toInstant(), returnDate.toInstant());
            // Exemple simple : 1 euro par jour de location
            double baseFeePerDay = 3.50;

            // Si le retour est en retard, appliquez un tarif différent
            if (isLate()) {
                double lateFeePerDay = 5.0; // Modifiez ceci selon votre logique de tarification pour les retards
                return (baseFeePerDay + lateFeePerDay) * daysRented;
            } else {
                return baseFeePerDay * daysRented;
            }
        } else {
            // La location est toujours en cours
            System.out.println("Movie not returned yet.");
            return 0.0;
        }
    }

    public void processReturn() {
        if (returnDate == null) {
            returnDate = new Date();
            System.out.println("Movie returned on: " + returnDate);

            double rentalFee;

            if (isLate()) {
                rentalFee = calculateRentalFee();
                System.out.println("Late return. Additional fee applied: " + rentalFee);
            } else {
                rentalFee = rentedMovie.getRentalRate();
                System.out.println("On-time return. Standard rental fee: " + rentalFee);
            }

            // Affichez le montant total sans effectuer de paiement
            System.out.println("Total amount to pay: " + rentalFee + " $");

        } else {
            System.out.println("Movie already returned on: " + returnDate);
        }
    }

    public boolean isLate() {
        if (returnDate != null) {
            // Si la date de retour est après la date prévue
            return returnDate.after(calculateDueDate());
        }
        // Si le film n'a pas encore été retourné, il n'est pas en retard
        return false;
    }
    public Date calculateDueDate() {
        Calendar dueDateCalendar = Calendar.getInstance();
        dueDateCalendar.setTime(rentalDate);
        dueDateCalendar.add(Calendar.DAY_OF_MONTH, 7); // Ajoutez 7 jours à la date de location
        return dueDateCalendar.getTime();
    }

    public static class Builder{

        private int rentalId;
        private Date rentalDate;
        private Date returnDate;
        private Movie rentedMovie;


        public Builder setRentalId(int rentalId){
            this.rentalId = rentalId;
            return this;
        }

        public Builder setRentalDate(Date rentalDate){
            this.rentalDate = rentalDate;
            return this;
        }

        public Builder setReturnDate(Date returnDate){
            this.returnDate = returnDate;
            return this;
        }

        public Builder setRentedMovie(Movie rentedMovie){
            this.rentedMovie = rentedMovie;
            return this;
        }

        public Builder copy(Rental rental){
            this.rentalId = rental.rentalId;
            this.rentalDate = rental.rentalDate;
            this.returnDate = rental.returnDate;
            this.rentedMovie = rental.rentedMovie;
            return this;
        }

        public Rental build(){
            return new Rental(this);
        }

    }

}
