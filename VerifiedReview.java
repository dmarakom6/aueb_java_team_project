
public class VerifiedReview extends Review {

    public VerifiedReview(VerifiedUser user, int rating, String comment, Movie movie) {
        super(user, rating, comment, movie);
    }

    public int getWeightedRating() {
        return Math.min(10, (int) Math.round(rating * 1.5)); // Cap weighted rating at 10
    }

}
