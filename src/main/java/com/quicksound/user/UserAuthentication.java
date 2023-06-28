package com.quicksound.user;

import com.quicksound.songs.Playlist;

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
}