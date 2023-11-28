package fr.efrei.domain;

import java.util.Date;

public class Movie {
    private int movieId;
    private String title;
    private Date releaseDate;
    private int availableCopies;

    // private Category category;

    private Movie() {}

    private Movie(Builder builder) {
        this.movieId = builder.movieId;
        this.title = builder.title;
        this.releaseDate = builder.releaseDate;
        this.availableCopies = builder.availableCopies;
        // this.category = builder.category;
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
    //public Category getCategory() {
    // return category;
    //}

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", availableCopies=" + availableCopies +
                //", category=" + category +
                '}';
    }
    public static class Builder {
        private int movieId;
        private String title;
        private Date releaseDate;
        private int availableCopies;
        //private Category category;

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
        //public Builder setCategory(Category category){
        //  this.category = category;
        //  return this;
        // }
        public Builder copy(Movie movie){
            this.movieId = movie.movieId;
            this.title = movie.title;
            this.releaseDate = movie.releaseDate;
            this.availableCopies = movie.availableCopies;
            //this.category = movie.category;
            return this;
        }
        public Movie build() {return new  Movie(this);}
    }
}
