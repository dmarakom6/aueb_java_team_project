import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// making the assumpion that only if a user has reviewed a movie it means he has seen it before.

public class Recommender {

    private User user; // The user for whom we want to recommend movies
    private List<Movie> moviesViewed; // The movies that the user has already (re)viewed
    private List<Movie> toRecommend = new ArrayList<>(); // The movies that we want to recommend to the user

    public Recommender(User user, List<Movie> moviesViewed) {
        this.user = user;
        this.moviesViewed = moviesViewed;
    }

    public void filterContentBased(List<Movie> movies, List<Review> reviews) {
        // Map genres with weighted ratings per movie from user
        Map<String, ArrayList<Integer>> genreRatings = new HashMap<>();
        for (Movie movie : movies) {
            // For each genre of the movie, add the rating to the map
            for (String genre : movie.getGenres()) {
                ArrayList<Integer> ratings = new ArrayList<>(); // should be empty after each iteration
                if (!genreRatings.containsKey(genre)) {
                    for (Review review : reviews) {
                        if (review.getMovie() == movie) {
                            // If the user has rated the movie, add the rating to the map
                            if (review.getUser() == user) {
                                ratings.add(review.getWeightedRating());
                                genreRatings.put(genre, ratings);
                            }
                            
                        }
                        
                    }

                }
            }
            
        }

        for (Map.Entry<String, ArrayList<Integer>> entry : genreRatings.entrySet()) {
            String genre = entry.getKey();
            ArrayList<Integer> ratings = entry.getValue();
            int sum = 0;
            for (int rating : ratings) {
                sum += rating;
            }
            int average = sum / ratings.size();
            genreRatings.put(genre, new ArrayList<Integer>(List.of(average))); // swap rating lists for list of averages
        }
        // Sort the map by the average rating of each genre (something like bubblesort)
        genreRatings.entrySet().stream()
            .sorted((entry1, entry2) -> Integer.compare(entry2.getValue().get(0), entry1.getValue().get(0)))
            .forEach(entry -> {
                String genre = entry.getKey();
                // For each movie in the list, if it has the same genre as the one with the highest rating, add it to the recommendation list
                for (Movie movie : movies) {
                    if (movie.getGenres().contains(genre) && !moviesViewed.contains(movie)) {
                        toRecommend.add(movie);
                    }
                }
            });
            System.out.println("Recommended movies based on your favourite genres and movie ratings:");
            for (Movie movie : toRecommend) {
                System.out.println(movie.getTitle());
            }
            System.out.println();


    }

 
    public void filterUserBased() {} //todo

}
