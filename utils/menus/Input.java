package utils.menus;

import java.util.Scanner;

public class Input { 

    private String message;
    private static final Scanner scanner = new Scanner(System.in); // will be reused

    Input() {
        this.message = "Select an option: ";
    }
    Input(String message) {
        this.message = message;
    }
    public int getInt() {
        System.out.print(message);
        int selection = -1;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }
        return selection;
    }

    public String getString() {
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

