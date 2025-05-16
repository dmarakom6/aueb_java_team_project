package types;
import types.interfaces.Printable;


public abstract class Review implements Printable {
    protected User user;
    protected int rating;
    protected String comment;
    protected Movie movie;

    public Review(User user, int rating, String comment, Movie movie) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public abstract int getWeightedRating();

    public void printDetails() {
    System.out.println(user.getUsername() + " rated " + movie.getTitle() + "with " + rating + "/10");
    if (comment != null && !comment.isEmpty())
    System.out.println("Comments: " + comment);
    }
    
    public String toString() {
        return user.getUsername() + " rated " + movie.getTitle() + " with " + rating + "/10" + "\nComments: " + (comment == "" ? "-" : comment);
    }
}
