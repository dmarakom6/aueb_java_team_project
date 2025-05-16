
public class BasicReview extends Review {

    public BasicReview(User user, int rating, String comment, Movie movie) {
        super(user, rating, comment, movie);
    }

    public int getWeightedRating() {
        return rating;
    }
}
