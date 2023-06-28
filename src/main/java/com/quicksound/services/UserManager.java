package com.quicksound.services;

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

    public boolean isUserRegistered(String[] userData){
        return UserManager.INSTANCE.getUsers().stream().anyMatch(
                user -> user.getName().equals(userData[0]) && user.getPassword().equals(userData[1]));
    }

    public boolean changeUserUsername(String newUsername){

        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();

        if (newUsername.equals(currentUser.getName()) || UserManager.INSTANCE.getUsers().stream().anyMatch(user -> user.getName().equals(newUsername))){
            System.out.println("Ese nombre ya se encuentra en uso.");
            return false;
        }
        currentUser.setUserName(newUsername);
        return true;
    }

    public boolean changeUserPassword(String newPassword){
        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();

        if (newPassword.equals(currentUser.getPassword())) {
            return false;
        }
        currentUser.setUserPassword(newPassword);
        return true;
    }

    public void deleteUser() {
        deleteUser(UserAuthentication.INSTANCE.getCurrentUser().getId());
        UserAuthentication.INSTANCE.logout();
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
