

public class ReviewToolsMenu extends Menu {
    @Override
    public void printMenu() {
        System.out.println("Main Menu > Review Tools");
        System.out.println("========================");
        System.out.println("1. Add Review");
        System.out.println("2. Get Review Details");
        System.out.println("3. Delete Review");
        System.out.println("4. Back to Main Menu");
    }

    public void clearScreen() {
        super.clearScreen();
    }
    
}
