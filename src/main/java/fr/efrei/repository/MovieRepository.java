package fr.efrei.repository;
import fr.efrei.domain.Movie;
import java.util.List;
import java.util.ArrayList;

public class MovieRepository implements IMovieRepository{
    private static MovieRepository repository = null;
    private List<Movie> movies;
    private MovieRepository(){
        this.movies = new ArrayList<Movie>();
    }
    public static MovieRepository getRepository(){
        if(repository == null){
            repository = new MovieRepository();
        }
        return repository;
    }
    @Override
    public Movie create(Movie movie){
        boolean success = movies.add(movie);
        if(success){
            return movie;
        }
        else {
            return null;
        }
    }
    @Override
    public Movie read(Long MovieId){
        for(Movie movie : movies){
            if(movie.getMovieId() == MovieId){
                return movie;
            }
        }
        return null;
    }
    @Override
    public Movie update(Movie movie){
        for(Movie m : movies){
            if(m.getMovieId() == movie.getMovieId()){
                movies.remove(m);
                movies.add(movie);
                return movie;
            }
        }
        return null;
    }
    @Override
    public boolean delete(Long MovieId) {
        Movie movieToDelete = read(MovieId);
        boolean success = movies.remove(movieToDelete);
        return success;
    }
    public Movie getById(int movieId) {
        for (Movie movie : movies) {
            if (movie.getMovieId() == movieId) {
                return movie;
            }
        }
        return null; // Retourne null si le film n'est pas trouvé
    }
    @Override
    public List<Movie> getAll() {
        return movies;
    }

}
