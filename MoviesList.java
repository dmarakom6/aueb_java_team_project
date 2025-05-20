
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getAverageRating(), m1.getAverageRating());
            }
        });
        return movies;
    }

    public List<Movie> sortMoviesByReviews() {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Integer.compare(m2.getReviews().size(), m1.getReviews().size());
            }
        });
        return movies;
    }
    public void printTopMoviesByGenre(int n) {
        Map<String, List<Movie>> genreMap = new HashMap<>();
        for (Movie movie : movies) {
            List<String> genres = movie.getGenres();
            if (genres != null && !genres.isEmpty()) {
                for (String genre : genres) {
                    genreMap.computeIfAbsent(genre, k -> new ArrayList<>()).add(movie);
                }
            }
        }

        for (Map.Entry<String, List<Movie>> entry : genreMap.entrySet()) {
            String genre = entry.getKey();
            List<Movie> genreMovies = entry.getValue();
            genreMovies.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    return Double.compare(m2.getAverageRating(), m1.getAverageRating());
                }
            });
            System.out.println("Genre: " + genre);
            for (int i=0; i < Math.min(n, genreMovies.size()); i++) {
                System.out.println(" - " + genreMovies.get(i).getTitle());
            }
        }
    }

    public void initializeSampleMovies() {
        // Sample movies
        Movie movie1 = new Movie("Inception", 2010, new User("JohnDoe"), List.of("Action", "Sci-Fi"),
                "Christopher Nolan", new ArrayList<>());
        Movie movie2 = new Movie("The Godfather", 1972, new User("JohnDoe"), List.of("Crime", "Drama"),
                "Francis Ford Coppola", new ArrayList<>());
        Movie movie3 = new Movie("The Dark Knight", 2008, new VerifiedUser("JaneSmith", "12345"),
                List.of("Action", "Crime"), "Christopher Nolan", new ArrayList<>());
        // Add sample movies to the list
        addMovie(movie1);
        addMovie(movie2);
        addMovie(movie3);
    }
}