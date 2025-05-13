package utils.menus;


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

    public void init() {
        this.clearScreen();
        this.printMenu();
    }

}


