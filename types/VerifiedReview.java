package types;

public class VerifiedReview extends Review {
    protected String verificationCode;

    public VerifiedReview(User user, int rating, String comment, Movie movie, String verificationCode) {
        super(user, rating, comment, movie);
        this.verificationCode = verificationCode;
    }

    public int getWeightedRating() {
        return Math.min(10, (int) Math.round(rating * 1.5)); // Cap weighted rating at 10
    }

    public String getVerificationCode() {
        return verificationCode;
    }
    
}
