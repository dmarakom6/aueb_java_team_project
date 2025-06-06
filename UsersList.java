

import java.util.ArrayList;
import java.util.Arrays;


public class UsersList {
    private ArrayList<User> users;

    public UsersList() {
        this.users = new ArrayList<>();
    }

    public void addUser(User User) {
        users.add(User);
    }

    public void removeUser(User User) {
        users.remove(User);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void initializeSampleUsers() {
        // Replace with actual User constructor and fields
        User user1 = new User("JohnDoe");
        User user2 = new VerifiedUser("JaneSmith", "12345");
        User user3 = new User("AliceBrown");

        users.addAll(Arrays.asList(user1, user2, user3));
    }
}