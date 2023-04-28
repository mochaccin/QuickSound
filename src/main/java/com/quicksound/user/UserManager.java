package com.quicksound.user;

import com.quicksound.AppController;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static UserManager instance = null;
    private List<User> users = new ArrayList<>();

    private UserManager() {}

    public static UserManager getInstance() {
        UserManager result = instance;
        if (result == null) {
            synchronized (AppController.class) {
                if (result == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public void registerUser(String username, String password) {
        users.add(new User(username, password));
    }

    public void updateUser(String username, String password, int userId) {
        users.get(userId).updateUserData(username, password);
    }

    public void updateUserName(String username, int userId) {
        users.get(userId).setUserName(username);
    }

    public void updateUserPassword(String password, int userId) {
        users.get(userId).setUserPassword(password);
    }

    public void updateUserId(int userId, int newUserId) {
        users.get(userId).setUserId(newUserId);
    }

    public void deleteUser(int userId) {
        users.remove(userId);
    }

    public int nextUserId() {
        return users.size()-1;
    }

    public void updateUsersIds(int index) {
        for (int i = index+1; i < users.size(); i++) {
            updateUserId(i, i-1);
        }
    }

}