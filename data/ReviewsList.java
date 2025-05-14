package data;

import java.util.ArrayList;

import types.Review;

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
}