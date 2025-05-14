package data;

import java.util.ArrayList;
import types.Movie;

public class MoviesList {
    private ArrayList<Movie> movies;

    public int size() {
        return movies.size();
    }

    public MoviesList() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public Movie getMovie(int i) {
        if (i >= 0 && i < movies.size()) {
            return movies.get(i);
        }
        return null;
    }
}