import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


// making the assumpion that only if a user has reviewed a movie it means he has seen it before.

public class Recommender implements Printable {

    private User user; // The user for whom we want to recommend movies
    private List<Movie> moviesViewed; // The movies that the user has already (re)viewed
    private List<Movie> toRecommend = new ArrayList<>(); // The movies that we want to recommend to the user

    public Recommender(User user, List<Movie> moviesViewed) {
        this.user = user;
        this.moviesViewed = moviesViewed;
    }

    public void filterContentBased(List<Movie> movies, List<Review> reviews) {
        // Map genres with average ratings from user
        Map<String, Double> genreRatings = new HashMap<>();
        for (Movie movie : movies) {
            // For each genre of the movie, add the rating to the map
            for (String genre : movie.getGenres()) {
                if (!genreRatings.containsKey(genre)) {
                    for (Review review : reviews) {
                        if (review.getMovie() == movie) {
                            // If the user has rated the movie, add the rating to the map
                            if (review.getUser() == user) {
                                genreRatings.put(genre, (double) review.getWeightedRating());
                            }
                            
                        }
                        
                    }

                }
            }
            
        }
        // Filter the movies that the Content has already viewed
        for (Movie movie : movies) {
            
            //todo
        }

    }

    @Override
    public void printDetails() {
        
    }



}
