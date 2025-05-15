/*
			1st team project for Java Class (INF358)
			========================================
			MEMBERS:
						-DIMITRIS EMMANOUIL *M*ARAKOMICHELAKIS | 3240236 | p3240126@aueb.gr
						-VASILIS  *D*IAMANTIDIS | 3240040 | p3240040@aueb.gr


*/

import utils.menus.*;

import java.util.ArrayList;
import java.util.Iterator;
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
					ArrayList<Movie> relmovies = new ArrayList<>();
					String t = menu.getString("Enter the title of the movie: ");
					int y = menu.getInt("Enter the year of the movie: ");
					String[] g = menu.getString("Enter the genres of the movie (comma separated): ").split(",");
					String d = menu.getString("Enter the director of the movie: ");
					String[] r = menu.getString("Enter related movies (comma separated or Enter to skip): ").split(",");
					for (String m : r) {
						for (Movie movietemp : moviesList.getMovies()) {
							if (movietemp.getTitle().equalsIgnoreCase(m)) {
								relmovies.add(movietemp);
							} else {
								System.out.println("Movie  '" + m + "' was not found.");
								System.exit(2);
							}
						}
					}
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
					Movie newMovie = new Movie(t, y, user, List.of(g), d, relmovies);
					newMovie.setUser(user);
					moviesList.addMovie(newMovie);
					System.out.println("Movie added successfully.");
					menu.sleep();
					break;
				case 2: // get details
					String searchinput = menu.getString("Enter the title/genre/rating of the movie: ");
					System.out.println("Movies matching your search: ");
					int i = 0; // number of results
					for (Movie movietemp : moviesList.getMovies()) { // for every movie in the current list of movies

						if (movietemp.getTitle().equalsIgnoreCase(searchinput)
								|| movietemp.getGenres().contains(searchinput)) {
							i++;
							System.out.print("(" + i + "): ");
							System.out.println(movietemp); // get details of each movie using the toString() method.
							menu.pressContinue();


						}
					}
					if (i == 0) {
						System.out.println("No movies with matching title/genre/rating found.");
						menu.pressContinue();
					}

					break;

				case 3: // delete movie
					boolean isVerified = false;
					boolean isFound = false;
					System.out.println(
							"Regular users can only remove their own movies.\nVerified users can remove any movie.\n");
					String uname = menu.getString("You are user: ");
					for (User u : usersList.getUsers()) {
						if (u.getUsername().equals(uname) && u.checkIsVerified()) {
							System.out.println("You are a verified user.");
							String vc = menu.getString("Enter your verification code: ");
							if (u.getVerificationCode().equals(vc)) {
								System.out.println("Verification code is correct.");
							} else {
								System.out.println("Verification code is incorrect.");
								System.exit(2);
							}
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
					Iterator<Movie> iterator = moviesList.getMovies().iterator();
					while (iterator.hasNext()) {
						Movie movie = iterator.next();
						if (movie.getTitle().equalsIgnoreCase(title)) {
							if (isVerified) {
								iterator.remove(); // Safely removes the movie
								System.out.println("Movie removed successfully.");
								menu.sleep();
							} else if (movie.getUser().getUsername().equals(uname)) {
								iterator.remove(); // Safely removes the movie
								System.out.println("Movie removed successfully.");
								menu.sleep();
							} else {
								System.out.println("You are not authorized to remove this movie.");
								menu.pressContinue();
							}
							break;
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
				case 1: // Add user
					String username = menu.getString("\nEnter new username: ");
					String verificationCode = menu.getString("Enter Verification Code (or Enter to skip): ");
					// TODO - vd
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
		moviesList.initializeSampleMovies(); // Initialize sample movies here
		reviewsList.initializeSampleReviews(); // Initialize sample reviews here
		// Initialize the menu
		Menu menu = new Menu();
		while (true) {
			Main.MainMenu(menu);
		}
	}
}