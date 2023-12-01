package fr.efrei.factory;
import fr.efrei.Util.Helper;
import fr.efrei.domain.Category;

public class CategoryFactory {
    public static Category createCategory(int categoryId, String name) {
        if (Helper.isStringValid(name) || Helper.isNumericEmpty(categoryId)) {
            return null;
        }
        return new Category.Builder()
                .setCategoryId(categoryId)
                .setName(name)
                .build();
    }

}
