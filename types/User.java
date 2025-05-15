package types;

import java.util.*;
import types.interfaces.Printable;

public class User implements Printable {
    protected String username;
    protected String verificationCode;
    protected List<Review> reviews;

    public User(String username) {
        this.username = username;
        this.reviews = new ArrayList<Review>();
    }

    public User(String username, String verificationCode) { // only for verified users
        this.username = username;
        this.verificationCode = verificationCode;
        this.reviews = new ArrayList<Review>();
    }

    public void addReview(Review r) {
        reviews.add(this.verificationCode == null ? r : (VerifiedReview) r); // new reviews are verified if user is too
    }

    public String getUsername() {
        return username;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

        public boolean checkIsVerified() {
        return this.verificationCode != null;
    }
    public void printDetails() {
        System.out.println("User: " + username);
        System.out.println("Reviews submitted: " + reviews.size());
    }
}