/*
			1st team project for Java Class (INF358)
			========================================
			MEMBERS:
						-DIMITRIS EMMANOUIL *M*ARAKOMICHELAKIS | 3240236 | p3240126@aueb.gr
						-VASILIS  *D*IAMANTIDIS | 3240040 | p3240040@aueb.gr


*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Predicate;

public class Main {

	public static MoviesList moviesList = new MoviesList();
	public static UsersList usersList = new UsersList();
	public static ReviewsList reviewsList = new ReviewsList();

	private static Predicate<String> notIn(List<String> list) { // function that returns a condition (predicate) which
																// on its turn returns true when an item is not in a
																// list
		return g -> !list.contains(g); // the condition is based on this lambda
	}

	// utilities
	public static void getMovieDetails(Menu menu) {
		String searchinput = menu.getString(
				"Enter the title/genre/rating of the movie: ");
		System.out.println("Movies matching your search: ");
		int i = 0; // number of results
		for (Movie movietemp : moviesList.getMovies()) { // for every movie in the current list of movies

			boolean matched = false;
			// Check if input is a number (for rating)
			try {
				double rating = Double.parseDouble(searchinput);
				if (Math.abs(movietemp.getAverageRating() - rating) < 0.01) {
					matched = true;
				}
			} catch (NumberFormatException e) {
				// Not a number, continue to check title and genres
			}
			// Check title and genres
			if (movietemp.getTitle().equalsIgnoreCase(searchinput)
					|| movietemp.getGenres().contains(searchinput)
					|| matched) {
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
	}

	public static void addMovie(Menu menu) {
		ArrayList<Movie> relmovies = new ArrayList<>();
		String t = menu.getString("Enter the title of the movie: ");
		int y = menu.getInt("Enter the year of the movie: ");
		String[] g = menu.getString("Enter the genres of the movie (comma separated): ").split(",");
		String d = menu.getString("Enter the director of the movie: ");
		String[] r = menu.getString("Enter related movies (comma separated or Enter to skip): ").split(",");
		int i = 0;
		for (String m : r) {
			for (Movie movietemp : moviesList.getMovies()) { // for every movie in the current list of movies
				if (movietemp.getTitle().equalsIgnoreCase(m.trim())) {
					relmovies.add(movietemp);
					i++;
					System.out.println("Movie  '" + m.trim() + "' was found.");
				} else if (m.isEmpty()) {
					continue;
				}
			}
		}
		System.out.println("Found " + i + "/" + r.length + " given related movies.");
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
	}

	public static void deleteMovie(Menu menu) {
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

	}

	public static void getTopMovies(Menu menu) {
		int l = moviesList.getMovies().size();
		int n = menu.getInt("Number of Movies to show (1-" + l + "): ");
		int r = menu.getInt("Minimum number of reviews: ");
		if (r < 0) {
			System.out.println("Invalid number. Please try again.");
			menu.pressContinue();
			return;
		}
		if (n < 1 || n > l) {
			System.out.println("Invalid number. Please try again.");
			menu.pressContinue();
			return;
		}
		System.out.println("Top " + n + " movies with more than " + r + " reviews:\n");
		List<Movie> topMovies = moviesList.sortMoviesByRating();
		for (int i = 0; i < Math.min(n, topMovies.size()); i++) {
			if (topMovies.get(i).getReviews().size() >= r) {
				System.out.println((i + 1) + ": " + topMovies.get(i));
			}
		}
		menu.pressContinue();
	}

	public static void compareMovies(Menu menu) {
		String title1 = menu.getString("Enter name of movie 1: ");
		String title2 = menu.getString("Enter name of movie 2: ");
		Movie movie1 = null;
		Movie movie2 = null;
		for (Movie movietemp : moviesList.getMovies()) {

			if (movietemp.getTitle().equalsIgnoreCase(title1)) {
				movie1 = movietemp;
				break;

			}
		}
		if (movie1 == null) {
			System.out.println("No movie with the first title was found.");
			System.exit(2);
		}
		for (Movie movietemp : moviesList.getMovies()) {
			if (movietemp.getTitle().equalsIgnoreCase(title2)) {
				movie2 = movietemp;
				break;
			}
		}
		if (movie2 == null) {
			System.out.println("No movie with the second title was found.");
			System.exit(2);
		}
		if (title1 == title2) {
			System.out.println("No differences found, movies typed were the same.");
		} else {
			System.out.println("Differences found: ");
			if (movie1.getYear() != movie2.getYear()) {
				System.out.println("Release year of " + title1 + ": " + movie1.getYear()
						+ "\nRelease year of " + title1 + ": " + movie2.getYear());
			}
			if (movie1.getDirector() != movie2.getDirector()) {
				System.out.println("Director of  " + title1 + ": " + movie1.getDirector()
						+ "\nDirector of  " + title2 + ": " + movie2.getDirector());
			}
		}
		List<String> gen1 = movie1.getGenres();
		List<String> gen2 = movie2.getGenres();
		List<String> difgen = Stream.concat(gen1.stream().filter(notIn(gen2)),
				gen2.stream().filter(notIn(gen1))).collect(Collectors.toList());
		if (difgen.isEmpty()) {

			System.out.println("The movies have the same genres.");

		} else {

			System.out.println("The different genres between the two movies are: " + difgen);

		}
		List<String> rev1 = new ArrayList<>();
		List<String> rev2 = new ArrayList<>();
		for (Review rev : movie1.getReviews()) {
			rev1.add(rev.toString());
		}
		for (Review rev : movie2.getReviews()) {
			rev2.add(rev.toString());
		}
		List<String> difrev = Stream.concat(rev1.stream().filter(notIn(rev2)),
				rev2.stream().filter(notIn(rev1))).collect(Collectors.toList());
		if (difrev.isEmpty()) {

			System.out.println("The movies have the same reviews.");

		} else {

			System.out.println("The different reviews between the two movies are: " + difrev);

		}
		menu.pressContinue();
	}

	public static void addReview(Menu menu) {
		boolean found = false;
		String movieTitle = menu.getString("Enter movie title: ");
		for (Movie movie : moviesList.getMovies()) {
			if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
				found = true;
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
					menu.pressContinue();
					break;
				}
				if (user.checkIsVerified()) {
					System.out.println("You are a verified user.");
					String verificationCode = menu.getString("Enter your verification code: ");
					if ((user.getVerificationCode().equals(verificationCode))) {
						System.out.println("Verification code is correct.");
					} else {
						System.out.println("Verification code is incorrect.");
						menu.pressContinue();
						break;
					}
				} else {
					System.out.println("You are a regular user.");
				}
				int rating = menu.getInt("Enter rating (integer 1-10): ");
				rating = menu.isValidRating(rating);
				if (rating == -1) {
					System.exit(2);
				}
				String comment = menu.getString("Enter comment (Enter to skip): ");
				Review review = user.checkIsVerified() ? new VerifiedReview((VerifiedUser) user, rating, comment, movie)
						: new BasicReview(user, rating, comment, movie);
				movie.addReview(review);
				user.addReview(review);
				reviewsList.addReview(review);
				System.out.println("Review added successfully.");
				menu.sleep();
			}

		}
		if (!found) {
			System.out.println("Movie not found.");
			menu.pressContinue();
		}

	}

	public static void getReviewDetails(Menu menu) {
		boolean found = false;
		String reviewTitle = menu.getString("Enter the title of the movie: ");
		for (Movie movie : moviesList.getMovies()) {
			if (movie.getTitle().equalsIgnoreCase(reviewTitle)) {
				found = true;
				System.out.println("Reviews for " + movie.getTitle() + ":");
				for (Review review : movie.getReviews()) {
					System.out.println(review);
				}
				menu.pressContinue();
			}

		}
		if (!found) {
			System.out.println("Movie not found.");
			menu.pressContinue();
		}
	}

	public static void deleteReview(Menu menu) {
		boolean found = false;
		String title = menu.getString("Enter the title of the movie: ");
		String username = menu.getString("Enter your username: ");
		for (Movie movie : moviesList.getMovies()) {
			if (movie.getTitle().equalsIgnoreCase(title)) {
				for (Review review : movie.getReviews()) {
					if (review.getUser().getUsername().equalsIgnoreCase(username)) {
						movie.removeReview(review);
						reviewsList.removeReview(review);
						for (User user : usersList.getUsers()) {
							if (user.getUsername().equalsIgnoreCase(username)) {
								user.removeReview(review);
							}
						}
						found = true;
						System.out.println("Review deleted successfully.");
						menu.sleep();
						break;
					}
				}
				if (!found) {
					System.out.println("Review by user '" + username + "' not found.");
					menu.pressContinue();
				}
			}
		}
	}

	public static void addUser(Menu menu) {
		boolean taken = false;
		String username;
		do {
			taken = false;
			username = menu.getString("\nEnter new username: ");
			for (User newuser : usersList.getUsers()) {
				if (username.equals(newuser.getUsername())) {
					System.out.println("Username is already taken. Try again.");
					taken = true;
					break;
				}
			}
		} while (taken);
		String verificationCode = menu.getString("Enter Verification Code (or Enter to skip): ");
		try {
			if (verificationCode.isEmpty()) {
				usersList.addUser(new User(username));
			} else {
				usersList.addUser(new VerifiedUser(username, verificationCode));
			}
			System.out.println("User added successfully.");
			menu.sleep();
		} catch (Exception e) {
			System.out.println("An error occurred while adding the user.");
			System.out.println("Please try again.");
			menu.sleep();
		}
	}

	public static void searchUser(Menu menu) {
		String usersearch = menu.getString("\nSearch username: ");
		boolean found = false;
		for (User usertemp : usersList.getUsers()) {
			if (usersearch.equals(usertemp.getUsername())) { // never use "==" for strings...
				found = true;
				usertemp.printDetails();
				break;
			}
		}
		if (!found) {
			System.out.println("Username does not exist.");
		}
		menu.pressContinue();
	}

	public static void initializeData(Menu menu) {
		usersList.initializeSampleUsers(); // Initialize sample users here
		moviesList.initializeSampleMovies(); // Initialize sample movies here and connect existin users
		// grab sample csv review data from the web
		ArrayList<List<String>> data = new Data().getData();
		// parse to the lists
		for (int i = 1; i < data.size(); i++) {
			List<String> line = data.get(i);
			String title = line.get(0);
			int year = Integer.parseInt(line.get(1));
			String[] genres = line.get(2).split(",");
			String username = line.get(3);
			int rating = Integer.parseInt(line.get(4));
			String comment = line.get(5);

			// Find or create user
			User user = null;
			for (User u : usersList.getUsers()) {
				if (u.getUsername().equals(username)) {
					user = u;
					break;
				}
			}
			if (user == null) {
				user = new User(username);
				usersList.addUser(user);
			}

			// Find or create movie
			Movie movie = null;
			for (Movie m : moviesList.getMovies()) {
				if (m.getTitle().equals(title)) {
					movie = m;
					break;
				}
			}
			if (movie == null) {
				movie = new Movie(title, year, user, List.of(genres), "Unknown", new ArrayList<>());
				moviesList.addMovie(movie);
			}

			// Create and add review
			Review review = user.checkIsVerified()
					? new VerifiedReview((VerifiedUser) user, rating, comment, movie)
					: new BasicReview(user, rating, comment, movie);
			movie.addReview(review);
			user.addReview(review);
			reviewsList.addReview(review);
		}

		for (Movie m : moviesList.getMovies()) {
			System.out.println(m);
		}
	}


// menus

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
				addMovie(menu);
				break;
			case 2: // get details
				getMovieDetails(menu);
				break;

			case 3: // delete movie
				deleteMovie(menu);
				break;
			case 4: // compare movies
				compareMovies(menu);
				break;
			case 5: // get top movies
				getTopMovies(menu);
				break;
			case 6: // back
				MainMenu(new Menu());
				return;
			default:
				System.out.println("Invalid selection. Please try again.");
		}
	} else if (menu instanceof UserToolsMenu) {
		switch (s) {
			case 1: // Add user
				addUser(menu);
				break;
			case 2: // Search user
				searchUser(menu);
				break;
			case 3: // back
				MainMenu(new Menu());
				return;
			default:
				System.out.println("Invalid selection. Please try again.");
		}
	} else if (menu instanceof ReviewToolsMenu) {
		switch (s) {
			case 1: // Add review
				addReview(menu);
				break;
			case 2: // Get Review details
				getReviewDetails(menu);
				break;
			case 3: // Delete review
				deleteReview(menu);
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
	// Initialize the menu
	Menu menu = new Menu();
	initializeData(menu);
	while (true) {
		MainMenu(menu);
	}
}
}