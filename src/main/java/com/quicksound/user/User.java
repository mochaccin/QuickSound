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
        this.name = userName;
    }

    public void setUserPassword(String userPassword) {
        this.password = userPassword;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public void updateUserData(String userName, String userPassword){
        setUserName(userName);
        setUserPassword(userPassword);
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist){
        playlists.remove(playlist);
    }

    public List<Playlist> getUserPlaylists(){
        return playlists;
    }

    public Playlist getPlaylist(int index){
        return playlists.get(index);
    }

    public int getPlaylistSize(){
        return playlists.size();
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