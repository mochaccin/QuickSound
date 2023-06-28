package com.quicksound.services;

import com.quicksound.models.User;
import com.quicksound.models.Playlist;

public enum UserAuthentication {
    INSTANCE;
    private User currentUser = null;
    public boolean authenticate(String username, String password) {
        return UserManager.INSTANCE.getUsers().stream().anyMatch(
                user -> user.getName().equals(username) && user.getPassword().equals(password)
        );
    }

    public boolean login(String username, String password) {
        if (authenticate(username, password)) {
            currentUser = UserManager.INSTANCE.getUsers().stream().filter(
                    user -> user.getName().equals(username) && user.getPassword().equals(password)
            ).toList().get(0);
            System.out.println("Inicio de sesion exitoso.");
            return true;
        } else {
            System.out.println("El inicio de sesion ha fallado.");
            return false;
        }
    }

    public boolean isUserLoggedIn(String username, String password){
        if (currentUser != null) {
            return username.equals(currentUser.getName()) && password.equals(currentUser.getPassword());
        } return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser(){

        if (currentUser != null) {
            return currentUser;
        } return null;
    }

    public void addPlaylist(String name) {
        currentUser.addPlaylist(new Playlist(name));
    }

    public boolean isInputStringValid(String input){
        return input.matches("[a-zA-Z\\d]+");
    }


}