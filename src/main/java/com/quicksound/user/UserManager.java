package com.quicksound.user;

import java.util.ArrayList;
import java.util.List;


public enum UserManager {
    INSTANCE;

    private List<User> users = new ArrayList<>();

    public void registerUser(String username, String password) {
        if (!checkDuplicateUser(username)){
            users.add(new User(username, password));
            System.out.println("Usuario registrado exitosamente.");
        } else {
            System.out.println("El usuario ya se encuentra registrado.");
        }
    }

    public boolean checkDuplicateUser(String username) {
        return users.stream().anyMatch(user -> user.getName().equals(username));
    }

    public void updateUserName(String username, int userId) {
        if (!checkDuplicateUser(username)) {
            users.get(userId).setUserName(username);
        } else {
            System.out.println("Ese nombre de usuario ya se encuentra registrado.");
        }
    }

    public void updateUserPassword(String password, int userId) {
        users.get(userId).setUserPassword(password);
    }

    public void updateUserId(int userId, int newUserId) {
        users.get(userId).setUserId(newUserId);
    }

    public void deleteUser(int userId) {

        if (!users.isEmpty()) {
            users.remove(userId);
        }

    }

    public int nextUserId() {
        if (users.isEmpty()) {
            return 0;
        }
        return users.size()-1;
    }

    public void updateUsersIds(int index) {

        if (!users.isEmpty() && index != users.size()-1) {
            for (int i = index+1; i < users.size(); i++) {
                updateUserId(i, i-1);
            }
        }
    }
    public List<User> getUsers() {
        return users;
    }
    public User getLastUser() {

        if (!users.isEmpty()) {
            return users.get(users.size()-1);
        } return null;
    }
    public void displayUsers() {

        if (!users.isEmpty()) {
            users.forEach(user -> System.out.println(user.toString()));
        } else {
            System.out.println("No hay usuarios registrados.");
        }
    }
}
