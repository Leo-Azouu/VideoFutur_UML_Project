package fr.efrei.domain;

import fr.efrei.repository.MovieRepository;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Movie {
    private int movieId;
    private String title;
    private Date releaseDate;
    private int availableCopies;
    private double rentalRate;
    private Category category;

    private Movie() {}

    private Movie(Builder builder) {
        this.movieId = builder.movieId;
        this.title = builder.title;
        this.releaseDate = builder.releaseDate;
        this.availableCopies = builder.availableCopies;
        this.rentalRate = builder.rentalRate;
        this.category = builder.category;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
    public double getRentalRate() {return rentalRate;}
    public Category getCategory() {return category;}
    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }
    private void increaseAvailableCopies() {
        availableCopies++;
    }
    public void returnMovie(User user) {
        // Vérifiez d'abord si l'utilisateur a des films à retourner
        if (user.getRentals().isEmpty()) {
            System.out.println("You have no movies to return.");
            return;
        }

        // Vérifiez la liste des locations de l'utilisateur pour trouver la location correspondante
        Rental rentalToRemove = null;

        for (Rental rental : user.getRentals()) {
            if (rental.getRentedMovie().equals(this) && rental.getRentalDate() != null && rental.getReturnDate() == null) {
                rental.processReturn();
                rentalToRemove = rental;

                // Mettez à jour le nombre de copies disponibles directement ici
                increaseAvailableCopies();

                break;
            }
        }

        if (rentalToRemove != null) {
            user.removeRental(rentalToRemove);  // Ajoutez une méthode removeRental dans la classe User
            System.out.println("Movie returned successfully by user: " + user.getUsername());
        } else {
            System.out.println("Failed to return the movie. It may not be rented by the user or already returned.");
        }
    }
    @Override
    public String toString() {
        return "Movie ID: " + movieId +
                ", Title: '" + title + '\'' +
                ", Release Date: " + releaseDate +
                ", Available Copies: " + availableCopies +
                ", Rental Rate: " + rentalRate +
                ", Category: " + category.getName();
    }

    public static class Builder {
        private int movieId;
        private String title;
        private Date releaseDate;
        private int availableCopies;
        private double rentalRate;
        private Category category;

        public Builder setMovieId(int movieId) {
            this.movieId = movieId;
            return this;
        }
        public Builder setTitle(String title){
            this.title = title;
            return this;
        }
        public Builder setReleaseDate(Date releaseDate){
            this.releaseDate = releaseDate;
            return this;
        }
        public Builder setAvailableCopies(int availableCopies){
            this.availableCopies = availableCopies;
            return this;
        }
        public Builder setRentalRate(double rentalRate){
            this.rentalRate = rentalRate;
            return this;
        }
        public Builder setCategory(Category category){
            this.category = category;
            return this;
        }
        public Builder copy(Movie movie){
            this.movieId = movie.movieId;
            this.title = movie.title;
            this.releaseDate = movie.releaseDate;
            this.availableCopies = movie.availableCopies;
            this.rentalRate = movie.rentalRate;
            this.category = movie.category;
            return this;
        }
        public Movie build() {return new  Movie(this);}
    }
}