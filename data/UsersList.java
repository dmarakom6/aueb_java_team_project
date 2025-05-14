package data;

import java.util.ArrayList;

import types.User;

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
}