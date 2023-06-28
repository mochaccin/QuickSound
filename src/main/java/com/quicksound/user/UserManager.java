package com.quicksound.user;

import com.quicksound.models.User;

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
    public List<User> getUsers() {
        return users;
    }
}
