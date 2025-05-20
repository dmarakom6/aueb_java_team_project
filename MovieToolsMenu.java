

public class MovieToolsMenu extends Menu {

    @Override
    public void printMenu() {
        System.out.println("Main Menu > Movie Tools");
        System.out.println("=======================");
        System.out.println("1. Add Movie");
        System.out.println("2. Get Movie Details"); // + avg rating
        System.out.println("3. Delete Movie");
        System.out.println("4. Compare Movies");
        System.out.println("5. List Movies"); // submenu
        System.out.println("6. Back to Main Menu");
    }

    public void clearScreen() {
        super.clearScreen();
    }

    
}
