package com.quicksound.app;

import com.quicksound.songs.*;
import com.quicksound.user.User;
import com.quicksound.user.UserAuthentication;
import com.quicksound.user.UserManager;

public enum AppController {
    INSTANCE;

    public void createPlaylist(User user, String name){
        user.addPlaylist(new Playlist(name));
    }

    public void playPlaylist(Playlist playlist){
        Player.INSTANCE.playPlaylist(playlist);
    }

    public void playSong(Song song) throws InterruptedException {
        Player.INSTANCE.play(song);
    }

    public Song searchSongById(int id) {
        return SongLibrary.INSTANCE.searchSongById(id);
    }

    public int getLibrarySize(){
        return SongLibrary.INSTANCE.getSongLibrarySize();
    }

    public User getCurrentUser() {
        return UserAuthentication.INSTANCE.getCurrentUser();
    }

    public boolean isUserRegistered(String[] userData){
        return UserManager.INSTANCE.getUsers().stream().anyMatch(
                user -> user.getName().equals(userData[0]) && user.getPassword().equals(userData[1]));
    }

    public boolean isUserLoggedIn(String username, String password){
        if (getCurrentUser()!= null) {
            return username.equals(getCurrentUser().getName()) && password.equals(getCurrentUser().getPassword());
        } return false;
    }

    public void registerUser(String username, String password){
        UserManager userManager = UserManager.INSTANCE;
        userManager.registerUser(username, password);
    }

    public void addSongToUserPlaylist(int playlistId, int id) {
        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();
        currentUser.getPlaylist(playlistId).addSongById(id);
    }

    // login method.
    public boolean login(String username, String password){
        return UserAuthentication.INSTANCE.login(username, password);
    }

    public void displaySongs(){
        SongLibrary.INSTANCE.displaySongs();
    }

    public void deleteUser() {
        UserManager.INSTANCE.deleteUser(UserAuthentication.INSTANCE.getCurrentUser().getId());
        UserAuthentication.INSTANCE.logout();
        System.out.println("Tu cuenta ha sido eliminada exitosamente");
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
    public void logout() {
        UserAuthentication.INSTANCE.logout();
    }
    public void loadSongsToLibrary(){
        SongLibrary.INSTANCE.loadLibrary();
    }

    public void clearSongLibrary() {
        SongLibrary.INSTANCE.clearLibrary();
    }

    public boolean isInputStringValid(String input){
        return input.matches("[a-zA-Z\\d]+");
    }
}
