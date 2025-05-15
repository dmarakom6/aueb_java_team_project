package data;

import java.util.ArrayList;
import java.util.List;

import types.*;


public class ReviewsList {
    private ArrayList<Review> reviews;

    public ReviewsList() {
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void initializeSampleReviews() {
        // Sample reviews
        BasicReview review1 = new BasicReview(new User("JohnDoe", ""), 5, "Amazing movie!", new Movie("Inception", 2010, new User("JohnDoe", ""), List.of("Action", "Sci-Fi"), "Christopher Nolan", new ArrayList<>()));
        BasicReview review2 = new BasicReview(new User("JaneSmith", "12345"), 4, "Great plot and characters.", new Movie("The Godfather", 1972, new User("JohnDoe", ""), List.of("Crime", "Drama"), "Francis Ford Coppola", new ArrayList<>()));
        VerifiedReview review3 = new VerifiedReview(new User("Alice", ""), 3, "Good, but not as great as I expected.", new Movie("The Dark Knight", 2008, new User("JaneSmith", "12345"), List.of("Action", "Crime"), "Christopher Nolan", new ArrayList<>()), "Verified Purchase");
        // Add sample reviews to the list
        addReview(review1);
        addReview(review2);
        addReview(review3);
    }
}