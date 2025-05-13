package types;

public class VerifiedReview extends Review {
    protected String verificationCode;

    public VerifiedReview(User user, int rating, String comment, Movie movie, String verificationCode) {
        super(user, rating, comment, movie);
        this.verificationCode = verificationCode;
    }

    public int getWeightedRating() {
        return (int) Math.round(rating * 1.5); // Verified reviews are worth more
    }

    public String getVerificationCode() {
        return verificationCode;
    }
    
}
