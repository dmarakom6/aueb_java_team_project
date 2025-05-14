/*
			1st team project for Java Class (INF358)
			========================================
			MEMBERS:
						-DIMITRIS EMMANOUIL *M*ARAKOMICHELAKIS | 3240236 | p3240126@aueb.gr
						-VASILIS  *D*IAMANTIDIS | 3240040 | p3240040@aueb.gr


*/

import utils.menus.*;

import java.util.ArrayList;
import java.util.List;

import data.*;
import types.*;

public class Main {

	public static MoviesList moviesList = new MoviesList();
	public static UsersList usersList = new UsersList();
	// usersList.initializeSampleUsers(); // Moved to the main method
	public static ReviewsList reviewsList = new ReviewsList();

	public static void MainMenu(Menu menu) {
		menu.init();
		Integer s = menu.getInt("Enter an option: ");
		switch (s) {
			case 1:
				menu = new MovieToolsMenu();
				break;
			case 2:
				menu = new UserToolsMenu();
				break;
			case 3:
				menu = new ReviewToolsMenu();
				break;
			case 4:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				if (s instanceof Integer) {
					System.out.println("An error occurred. Rerun the program and try again.");
				}
				System.exit(2);
		}

		InnerMenu(menu);

	}

	public static void InnerMenu(Menu menu) {
		menu.init();
		int s = menu.getInt("Enter an option: ");
		if (menu instanceof MovieToolsMenu) {
			switch (s) {
				case 1: // add movie
					String t = menu.getString("Enter the title of the movie: ");
					int y = menu.getInt("Enter the year of the movie: ");
					String[] g = menu.getString("Enter the genres of the movie (comma separated): ").split(",");
					String d = menu.getString("Enter the director of the movie: ");
					String username = menu.getString("Enter your username: ");
					User user = null;
					for (User u : usersList.getUsers()) {
						if (u.getUsername().equals(username)) {
							user = u;
							break;
						}
					}
					if (user == null) {
						System.out.println("User not found.");
						System.exit(2);
					}
					Movie newMovie = new Movie(t, y, List.of(g), d);
					newMovie.setUser(user);
					moviesList.addMovie(newMovie);
					System.out.println("Movie added successfully.");

					break;
				case 2:
					// Get details
					String searchinput = menu.getString("Enter the title/genre/rating of the movie: ");
					int timesfound = 0;
					for (int i = 0; i < moviesList.size(); i++) {
						Movie movietemp = moviesList.getMovie(i);

						if (movietemp.getTitle().equalsIgnoreCase(searchinput)) {
							timesfound += 1;
							String title123 = movietemp.getTitle();
							String year123 = String.valueOf(movietemp.getYear());
							String genre123 = (movietemp.getGenres()).toString();
							String director123 = movietemp.getDirector();
							String reviews123 = (movietemp.getReviews()).toString();
							String relmovies123 = (movietemp.getRelatedMovies()).toString();
							if (timesfound == 1) {
								System.err.println("Movies matching your search: ");
							}
							System.out.println("Result " + timesfound + ":");
							System.out.println("\n" + "Title: " + title123);
							System.out.println("Year of Release: " + year123);
							System.out.println("Genre/Genres: " + genre123);
							System.out.println("Director: " + director123);
							System.out.println("Reviews: " + reviews123);
							System.out.println("Relevant Movies: " + relmovies123);

						}
					}
					if (timesfound == 0) {
						System.out.println("No movies with this title were found.");
					}
					// genre starts here
					int timesfound3 = 0;
					for (int i2 = 0; i2 < moviesList.size(); i2++) {
						Movie movietemp3 = moviesList.getMovie(i2);
						List<String> genrestemp = movietemp3.getGenres();
						for (int i3 = 0; i3 < genrestemp.size(); i3++) {
							if ((genrestemp.get(i3)).equalsIgnoreCase(searchinput)) {
								timesfound3 += 1;
								String title12 = movietemp3.getTitle();
								String year12 = String.valueOf(movietemp3.getYear());
								String genre12 = (movietemp3.getGenres()).toString();
								String director12 = movietemp3.getDirector();
								String reviews12 = (movietemp3.getReviews()).toString();
								String relmovies12 = (movietemp3.getRelatedMovies()).toString();
								if (timesfound3 == 1) {
									System.err.println("Movies matching your search: ");
								}
								System.out.println("Result " + timesfound3 + ":");
								System.out.println("\n" + "Title: " + title12);
								System.out.println("Year of Release: " + year12);
								System.out.println("Genre/Genres: " + genre12);
								System.out.println("Director: " + director12);
								System.out.println("Reviews: " + reviews12);
								System.out.println("Relevant Movies: " + relmovies12);

							}
						}
						if (timesfound3 == 0) {
							System.out.println("No movies with this genre were found.");
						}
						// rating starts here
						double ratingIwant = -1.0;
						try {
							int ratingInt = Integer.parseInt(searchinput);
							ratingIwant = Double.valueOf(ratingInt);
						} catch (NumberFormatException exInt) {
							try {
								ratingIwant = Double.parseDouble(searchinput);
							} catch (NumberFormatException exDouble) {

							}
						}
						if (ratingIwant > -1.0) {
							int timesfound2 = 0;
							for (int i = 0; i < moviesList.size(); i++) {
								Movie movietemp2 = moviesList.getMovie(i);
								if (movietemp2.getAverageRating() == ratingIwant) {
									timesfound2 += 1;
									String title1 = movietemp2.getTitle();
									String year1 = String.valueOf(movietemp2.getYear());
									String genre1 = (movietemp2.getGenres()).toString();
									String director1 = movietemp2.getDirector();
									String reviews1 = (movietemp2.getReviews()).toString();
									String relmovies1 = (movietemp2.getRelatedMovies()).toString();
									if (timesfound == 1) {
										System.err.println("Movies with the rating you typed: ");
									}
									System.out.println("Result " + timesfound2 + ":");
									System.out.println("\n" + "Title: " + title1);
									System.out.println("Year of Release: " + year1);
									System.out.println("Genre/Genres: " + genre1);
									System.out.println("Director: " + director1);
									System.out.println("Reviews: " + reviews1);
									System.out.println("Relevant Movies: " + relmovies1);

								}

							}
						}
						break;
					}

				case 3: // delete movie
					boolean isVerified = false;
					boolean isFound = false;
					System.out.println(
							"Regular users can only remove their own movies.\nVerified users can remove any movie.\n");
					String uname = menu.getString("You are user: ");
					for (User u : usersList.getUsers()) {
						if (u.getUsername().equals(uname) && u.checkIsVerified()) {
							System.out.println("You are a verified user.");
							isVerified = isFound = true;
							break;
						} else if (u.getUsername().equals(uname) && !u.checkIsVerified()) {
							System.out.println("You are a regular user.");
							isFound = true;
							break;
						}
					}
					if (!isFound) {
						System.out.println("User not found.");
						System.exit(2);
					}
					String title = menu.getString("Enter the title of the movie to remove: ");
					for (Movie movie : moviesList.getMovies()) {
						if (movie.getTitle().equals(title)) {
							if (isVerified) {
								moviesList.removeMovie(movie);
								System.out.println("Movie removed successfully.");
							} else if (movie.getUser().getUsername().equals(uname)) {
								moviesList.removeMovie(movie);
								System.out.println("Movie removed successfully.");
							} else {
								System.out.println("You are not authorized to remove this movie.");
							}
						} else {
							System.out.println("Movie not found.");
							System.exit(2);
						}
					}

					break;
				case 4: // compare movies

					break;
				case 5: // back
					MainMenu(new Menu());
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		} else if (menu instanceof UserToolsMenu) {
			switch (s) {
				case 1:
					// Add user
					break;
				case 2:
					// Remove user
					break;
				case 3:
					// Search user
					break;
				case 4:
					MainMenu(new Menu());
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		} else if (menu instanceof ReviewToolsMenu) {
			switch (s) {
				case 1:
					// Add review
					break;
				case 2:
					// Get review details
					break;
				case 3:
					// Delete review
					break;
				case 4: // back
					MainMenu(new Menu());
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		} else {
			System.out.println("An error occurred. Rerun the program and try again.");
			System.exit(2);

		}

	}

	public static void main(String[] args) {
		usersList.initializeSampleUsers(); // Initialize sample users here
		Menu menu = new Menu();
		Main.MainMenu(menu);
	}
}