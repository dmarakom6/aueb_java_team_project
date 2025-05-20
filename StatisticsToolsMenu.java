public class StatisticsToolsMenu extends Menu {

    @Override
    public void printMenu() {
        System.out.println("Main Menu > Statistics Tools");
        System.out.println("============================");
        System.out.println("1. Top 5 Movies by Genre");
        System.out.println("2. Average Ratings by User"); 
        System.out.println("3. Trending Movies"); 
        System.out.println("4. Back to Main Menu");
    }

    public void clearScreen() {
        super.clearScreen();
    }

}
