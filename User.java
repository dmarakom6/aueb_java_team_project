import java.util.*;


public class User implements Printable {
    protected String username;
    protected List<Review> reviews;

    public User(String username) {
        this.username = username;
        this.reviews = new ArrayList<Review>();
    }


    public void addReview(Review r) {
        reviews.add(r);
    }

    public void removeReview(Review r) {
        reviews.remove(r);
    }

    public String getUsername() {
        return username;
    }

    public String getVerificationCode() {
        return null; // Password is not stored in this class
    }

    public boolean checkIsVerified() {
     return false; // User is not verified by default
    }
    public void printDetails() {
        System.out.println("User: " + username + " (Not Verified)");
        System.out.println("Reviews submitted: " + reviews.size());
    }
}