package fr.efrei.factory;
import fr.efrei.Util.Helper;
import fr.efrei.domain.Category;
import fr.efrei.domain.Movie;
import java.util.Date;

public class MovieFactory {
    public static Movie createMovie(int movieId, String title, Date releaseDate, int availableCopies, Category category) {
        if (Helper.isStringValid(title) || Helper.isDateValid(releaseDate) || Helper.isNumericEmpty(availableCopies) || Helper.isCategoryValid(category) || Helper.isNumericEmpty(movieId)) {
            return null;
        }
        return new Movie.Builder()
                .setMovieId(movieId)
                .setTitle(title)
                .setReleaseDate(releaseDate)
                .setAvailableCopies(availableCopies)
                .setCategory(category)
                .build();
    }
}
