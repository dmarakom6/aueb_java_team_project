package types;

import java.util.*;
import types.interfaces.Printable;

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

    public String getUsername() {
        return username;
    }

    public void printDetails() {
        System.out.println("User: " + username);
        System.out.println("Reviews submitted: " + reviews.size());
    }
}