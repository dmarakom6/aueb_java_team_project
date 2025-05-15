package data;

import java.util.ArrayList;
import java.util.List;

import types.Movie;
import types.User;

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

    public void initializeSampleMovies() {
        // Sample movies
        Movie movie1 = new Movie("Inception", 2010, new User("JohnDoe", ""), List.of("Action", "Sci-Fi"), "Christopher Nolan");
        Movie movie2 = new Movie("The Godfather", 1972, new User("JohnDoe", ""), List.of("Crime", "Drama"), "Francis Ford Coppola");
        Movie movie3 = new Movie("The Dark Knight", 2008, new User("JaneSmith", "12345"), List.of("Action", "Crime"), "Christopher Nolan");
        // Add sample movies to the list
        addMovie(movie1);
        addMovie(movie2);
        addMovie(movie3);
    }
}