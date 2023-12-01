package fr.efrei.repository;
import fr.efrei.domain.Category;
import java.util.List;
public interface ICategoryRepository extends IRepository<Category,Long> {
    public List<Category> getAll();

}
