public class ListMoviesMenu extends Menu {
     @Override
    public void printMenu() {
        System.out.println("Main Menu > Movie Tools > List Movies");
        System.out.println("=====================================");
        System.out.println("1. Organize by Genre");
        System.out.println("2. List Top Movies by Rating"); // highest avgrating
        System.out.println("3. List Top Movies by Rating and Reviews"); // most reviews in reviewsList
        System.out.println("4. Back to Movies Menu");
    }

    public void clearScreen() {
        super.clearScreen();
    }

}
