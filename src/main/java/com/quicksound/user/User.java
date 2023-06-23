package com.quicksound.user;

import com.quicksound.songs.Playlist;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private int id;

    private List<Playlist> playlists = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        id = UserManager.INSTANCE.nextUserId();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setUserName(String userName) {
        if (!name.equals(userName)) {
            this.name = userName;
        }
    }

    public void setUserPassword(String userPassword) {
        if (!password.equals(userPassword)) {
            this.password = userPassword;
        }
    }

    public void setUserId(int newId) {
        if (id != newId) {
            this.id = newId;
        }
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist){

        if (!playlists.isEmpty() && playlists.contains(playlist)) {
            playlists.remove(playlist);
        } else {
            System.out.println("Usted no tiene ninguna playlist.");
        }

    }

    public List<Playlist> getUserPlaylists(){
        return playlists;
    }

    public Playlist getPlaylist(int index){

        if (!playlists.isEmpty()) {
            return playlists.get(index);
        } else {
            System.out.println("Usted no tiene ninguna playlist.");
            return null;
        }
    }

    public int getPlaylistsSize(){
        return playlists.size();
    }

    public void displayUserPlaylists() {

        if (!playlists.isEmpty()) {
            System.out.println("Playlists en tu biblioteca:");
            int playlistId = 0;

            for (Playlist playlist : playlists) {
                System.out.println("["+playlistId+"] " + playlist.toString());
                playlistId++;
            }
        } else {
            System.out.println("Usted no tiene ninguna playlist.");
        }
    }

    @Override
    public String
    toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}