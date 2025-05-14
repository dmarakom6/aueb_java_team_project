package utils.menus;

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

