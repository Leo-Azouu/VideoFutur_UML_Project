package fr.efrei.repository;
import fr.efrei.domain.Movie;
import java.util.List;
public interface IMovieRepository extends IRepository<Movie,Long>{
    public List<Movie> getAll();

}
