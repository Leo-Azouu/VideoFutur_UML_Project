package fr.efrei.factory;
import fr.efrei.Util.Helper;
import fr.efrei.domain.Category;
import fr.efrei.domain.Movie;

import java.util.List;

public class CategoryFactory {
    public static Category createCategory(int categoryId, String name, List <Movie> movies) {
        if (Helper.isStringValid(name) || Helper.isNumericEmpty(categoryId)) {
            return null;
        }
        return new Category.Builder()
                .setCategoryId(categoryId)
                .setName(name)
                .setMovies(movies)
                .build();
    }

}
