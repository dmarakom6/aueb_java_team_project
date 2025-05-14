/*
			1st team project for Java Class (INF358)
			========================================
			MEMBERS:
						-DIMITRIS EMMANOUIL *M*ARAKOMICHELAKIS | 3240236 | p3240126@aueb.gr
						-VASILIS  *D*IAMANTIDIS | 3240040 | p3240040@aueb.gr


*/

import utils.menus.*;

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
				case 1:
					// Add movie
					break;
				case 2:
				// Get details
					break;

				case 3:
					boolean isVerified = false;
					boolean isFound = false;
					System.out.println(
							"Regular users can only remove their own movies.\nVerified users can remove any movie.\n");
					String username = menu.getString("You are user: ");
					for (User user : usersList.getUsers()) {
						if (user.getUsername().equals(username) && user.checkIsVerified()) {
							System.out.println("You are a verified user.");
							isVerified = isFound = true;
							break;
						} else if (user.getUsername().equals(username) && !user.checkIsVerified()) {
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
							} else if (movie.getUser().getUsername().equals(username)) {
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
				case 4:
					// compare movie
					break;
				case 5:
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
					// Remove review
					break;
				case 3:
					// Search review
					break;
				case 4:
					// List reviews
					break;
				case 5:
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
		// Movie m = new Movie("Inception", 2010, List.of("Sci-Fi", "Action"),
		// "Christopher Nolan");
		// User u = new User("alice");
		// Review r = new BasicReview(u, 9, "Mind-blowing!", m);
		// m.addReview(r);
		// u.addReview(r);
		// m.printDetails();
		// System.out.println();
		// u.printDetails();
		// System.out.println();
		// r.printDetails();
		usersList.initializeSampleUsers(); // Initialize sample users here
		Menu menu = new Menu();
		Main.MainMenu(menu);
	}
}