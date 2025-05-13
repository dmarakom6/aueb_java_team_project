package utils.menus;

public class UserToolsMenu extends Menu {
    public void printMenu() {
        System.out.println("Main Menu > User Tools");
        System.out.println("======================");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. List Users");
        System.out.println("4. Back to Main Menu");
    }

    public void clearScreen() {
        super.clearScreen();
    }
    
}
