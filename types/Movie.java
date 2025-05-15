package types;

import java.util.*;
import types.interfaces.Printable;

public class Movie implements Printable {
    private String title;
    private User user; // user that added the movie
    private int year;
    private List<String> genres;
    private String director;
    private List<Review> reviews;
    private List<Movie> relatedMovies;

    public Movie(String title, int year, User user, List<String> genres, String director) {
        this.title = title;
        this.year = year;
        this.user = user;
        this.genres = new ArrayList<>(genres);
        this.director = director;
        this.reviews = new ArrayList<Review>();
        this.relatedMovies = new ArrayList<Movie>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getGenres() {
        return new ArrayList<>(genres);
    }

    public void setGenres(List<String> genres) {
        this.genres = new ArrayList<>(genres);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }

    public List<Movie> getRelatedMovies() {
        return new ArrayList<>(relatedMovies);
    }

    public void setRelatedMovies(List<Movie> relatedMovies) {
        this.relatedMovies = new ArrayList<>(relatedMovies);
    }

    public void addReview(Review r) {
        reviews.add((BasicReview) r);
    }

    public void addVerifiedReview(VerifiedReview r) {
        reviews.add(r);
    }

    public void updateReview(Review oldReview, Review newReview) {
        int index = reviews.indexOf(oldReview);
        if (index != -1) {
            reviews.set(index, newReview);
        }
    }

    public void removeReview(Review r) {
        reviews.remove(r);
    }

    public void addRelatedMovie(Movie m) {
        relatedMovies.add(m);
    }

    public double getAverageRating() {
        if (reviews.isEmpty())
            return 0;
        int total = 0;
        for (Review r : reviews) {
            total += r.getWeightedRating();
        }
        return (double) total / reviews.size();
    }

        public void printRelatedMovies() {
        System.out.println("Related Movies: ");
        for (Movie m : relatedMovies) {
            System.out.println(m.getTitle());
        }
    }

    public void printDetails() {
        System.out.println("Title: " + title);
        System.out.println("Added by: " + user.username + " " + (user.getVerificationCode() == null ? "(Verified)" : "(Not Verified)"));
        System.out.println("Year: " + year);
        System.out.println("Genres: " + genres);
        System.out.println("Director: " + director);
        this.printRelatedMovies();
        System.out.println("Ratings: " + reviews + " (" + getAverageRating() + ")\n");
    }

}