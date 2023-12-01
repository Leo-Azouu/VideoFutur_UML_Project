package fr.efrei.repository;
import fr.efrei.domain.Category;
import java.util.List;
import java.util.ArrayList;
public class CategoryRepository implements ICategoryRepository{
    private static CategoryRepository repository = null;
    private List<Category> categories;
    private CategoryRepository(){
        this.categories = new ArrayList<Category>();
    }
    public static CategoryRepository getRepository(){
        if(repository == null){
            repository = new CategoryRepository();
        }
        return repository;
    }
    @Override
    public Category create(Category category){
        boolean success = categories.add(category);
        if(success){
            return category;
        }
        else {
            return null;
        }
    }
    @Override
    public Category read(Long CategoryId){
        for(Category category : categories){
            if(category.getCategoryId() == CategoryId){
                return category;
            }
        }
        return null;
    }
    @Override
    public Category update(Category category){
        for(Category c : categories){
            if(c.getCategoryId() == category.getCategoryId()){
                categories.remove(c);
                categories.add(category);
                return category;
            }
        }
        return null;
    }
    @Override
    public boolean delete(Long CategoryId) {
        Category categoryToDelete = read(CategoryId);
        boolean success = categories.remove(categoryToDelete);
        return success;
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

}
