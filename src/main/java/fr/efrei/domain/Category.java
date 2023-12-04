package fr.efrei.domain;
import java.util.ArrayList;
import java.util.List;
public class Category {
    private int categoryId;
    private String name;
    private List<Movie> movies;

    private Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
        System.out.println("Movie added to the category successfully");
    }

    public void removeMovie(Movie movie) {
        if (movies.remove(movie))
            System.out.println("Movie removed from the category successfully");
        else
            System.out.println("Movie not found in the category");
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", movies=" + (movies != null ? movies.toString() : "null") +
                '}';
    }

    private Category(Builder builder) {
        this.categoryId = builder.categoryId;
        this.name = builder.name;
        this.movies = builder.movies != null ? new ArrayList<>(builder.movies) : new ArrayList<>();
    }

    public static class Builder {
        private int categoryId;
        private String name;
        private List<Movie> movies;

        public Builder setCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setMovies(List<Movie> movies) {
            this.movies = movies;
            return this;
        }

        public Builder copy(Category category) {
            this.categoryId = category.categoryId;
            this.name = category.name;
            this.movies = category.movies;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
