package com.quicksound.app;

import com.quicksound.songs.*;
import com.quicksound.user.User;
import com.quicksound.user.UserAuthentication;
import com.quicksound.user.UserManager;

import java.util.Arrays;

public enum AppController {
    INSTANCE;

    public void addSongToLibrary(Song song){
        SongLibrary.INSTANCE.addSong(song);
    }

    public void createPlaylist(User user, String name){
        user.addPlaylist(new Playlist(name));
    }

    public void playPlaylist(Playlist playlist){
        Player.INSTANCE.playPlaylist(playlist);
    }

    public void playSong(Song song) throws InterruptedException {
        Player.INSTANCE.play(song);
    }
    public void pauseSong(){
        Player.INSTANCE.pause();
    }
    public void resumeSong(){
        Player.INSTANCE.resume();
    }
    public void stopSong(){
        Player.INSTANCE.stop();
    }

    public void displaySongs(){
        SongLibrary.INSTANCE.displaySongs();
    }

    public void displayMenu() throws InterruptedException {
        Menu.INSTANCE.displayMenu();
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
            System.out.println("La nueva contraseña es la misma.");
            return false;
        }
        currentUser.setUserPassword(newPassword);
        return true;
    }

    public void loadSongsToLibrary(){
        SongLibrary.INSTANCE.loadLibrary();
    }
}
