/*
			1st team project for Java Class (INF358)
			========================================
			MEMBERS:
						-DIMITRIS EMMANOUIL *M*ARAKOMICHELAKIS | 3240236 | p3240126@aueb.gr
						-VASILIS  *D*IAMANTIDIS | 3240040 | p3240040@aueb.gr


*/


import utils.menus.*;

import data.*;

public class Main {
	
	public MoviesList moviesList = new MoviesList();
	public UsersList usersList = new UsersList();
	public ReviewsList reviewsList = new ReviewsList();

	public static void MainMenu(Menu menu) {
		menu.init();
		Integer s = menu.getInt();
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
		int s = menu.getInt();
		if (menu instanceof MovieToolsMenu) {
			switch (s) {
				case 1:
					// Add movie
					break;
				case 2:
					// Remove movie
					break;
				case 3:
					// Search movie, to be implemented inside a utility
					
					break;
				case 4:
					// List movies
					break;
				case 5:

					break;
				case 6:
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
		Menu menu = new Menu();
		Main.MainMenu(menu);
	}
}