package fr.efrei.factory;
import fr.efrei.Util.Helper;
import fr.efrei.domain.Category;
import fr.efrei.domain.Movie;
import fr.efrei.repository.CategoryRepository;

import java.util.Date;

public class MovieFactory {
    public static Movie createMovie(int movieId, String title, String releaseDate, int availableCopies, int categoryId, double rentalRate){
        if (Helper.isStringValid(title) || Helper.isStringValid(releaseDate) || Helper.isNumericEmpty(availableCopies) || Helper.isNumericEmpty(categoryId) || Helper.isDoubleEmpty(rentalRate)) {
            return null;
        }

        // Assuming CategoryRepository has a method to fetch a Category by ID
        Category category = CategoryRepository.getRepository().read((long) categoryId);

        if (category == null) {
            System.out.println("Category not found for ID: " + categoryId);
            return null;
        }

        Date parsedReleaseDate = Helper.parseStringToDate(releaseDate);

        if (parsedReleaseDate == null) {
            System.out.println("Invalid release date format.");
            return null;
        }

        return new Movie.Builder()
                .setMovieId(movieId)
                .setTitle(title)
                .setReleaseDate(parsedReleaseDate)
                .setAvailableCopies(availableCopies)
                .setCategory(category)
                .setRentalRate(rentalRate)
                .build();
    }
}
