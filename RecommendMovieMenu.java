public class RecommendMovieMenu extends Menu {
    @Override
    public void printMenu() {
        System.out.println("Main Menu > Recommend a Movie");
        System.out.println("=============================");
        System.out.println("1. Using Content-Based Filtering Algorithm");
        System.out.println("2. Using User-Based Filtering Algorithm");
        System.out.println("3. Back to Main Menu\n");
    }

    public void clearScreen() {
        super.clearScreen();
    }

}
