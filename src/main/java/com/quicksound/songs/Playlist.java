package com.quicksound.songs;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

}
