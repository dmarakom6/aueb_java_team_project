

import java.util.Scanner;

public class Input { 

    private static final Scanner scanner = new Scanner(System.in); // will be reused


    public int getInt(String message) {
        System.out.print(message);
        int selection = -1;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }
        return selection;
    }

    public int isValidRating(int rating) {
        if (rating < 0 || rating > 10) {
            System.out.println("Invalid rating. Please enter a rating between 0 and 10.");
            return -1;
        }
        return rating;
    }


    public String getString(String message) {
        System.out.print(message);
        String selection = "";
        try {
            selection = scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a string.");
        }
        return selection;
    }
}

