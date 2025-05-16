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

    public List<Movie> sortMoviesByRating() {
        movies.sort(new java.util.Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getAverageRating(), m1.getAverageRating());
            }
        });
        return movies;
    }

    public void initializeSampleMovies() {
        // Sample movies
        Movie movie1 = new Movie("Inception", 2010, new User("JohnDoe", ""), List.of("Action", "Sci-Fi"), "Christopher Nolan", new ArrayList<>());
        Movie movie2 = new Movie("The Godfather", 1972, new User("JohnDoe", ""), List.of("Crime", "Drama"), "Francis Ford Coppola", new ArrayList<>());
        Movie movie3 = new Movie("The Dark Knight", 2008, new User("JaneSmith", "12345"), List.of("Action", "Crime"), "Christopher Nolan", new ArrayList<>());
        // Add sample movies to the list
        addMovie(movie1);
        addMovie(movie2);
        addMovie(movie3);
    }
}