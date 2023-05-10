package com.quicksound.user;

import java.util.ArrayList;
import java.util.List;

public enum UserManager {
    INSTANCE;

    private List<User> users = new ArrayList<>();

    public void registerUser(String username, String password) {
        if (!checkDuplicateUser(username)){
            users.add(new User(username, password));
        } else {
            System.out.println("El usuario ya se encuentra registrado.");
        }
    }

    public boolean checkDuplicateUser(String username) {
        return users.stream().anyMatch(user -> user.getName().equals(username));
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
    public List<User> getUsers() {
        return users;
    }
}
