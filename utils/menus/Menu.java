package utils.menus;

import java.util.concurrent.TimeUnit;

public class Menu extends Input {
    public void printMenu() {
        System.out.println("Movie Review System 1.0");
        System.out.println("=======================");
        System.out.println("1. Movie Tools");
        System.out.println("2. User Tools");
        System.out.println("3. Review Tools");
        System.out.println("4. Exit\n");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    public void pressContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public void init() {
        this.clearScreen();
        this.printMenu();
    }

}
