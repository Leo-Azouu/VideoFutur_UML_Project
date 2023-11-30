package fr.efrei.domain;

import java.time.LocalDate;
import java.time.ZoneId;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public Movie getRentedMovie() {
        return rentedMovie;
    }

    public double calculateRentalFee(){
        return 0.0;
    }

    public void processReturn(){

    }

    public boolean isLate(){
        LocalDate localReturnDate = returnDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return LocalDate.now().isAfter(localReturnDate);
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

//    Rental Class:
//
//        Attributes:
//        rentalId: int
//        rentalDate: Date
//        returnDate: Date
//        rentedMovie: Movie
//        Methods:
//        calculateRentalFee(): double
//        processReturn(): void
//        isLate(): boolean