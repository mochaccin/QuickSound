package com.quicksound.songs;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addSongById(int index) {
        songs.add(SongLibrary.INSTANCE.searchSongById(index));
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Song getSongById(int index) {
        return songs.get(index);
    }

    public String getName() {
        return name;
    }

    public void displaySongs(){
        songs.forEach(song -> System.out.println("["+songs.indexOf(song)+"]" + song.toString()));
    }

    public int getSize() {
        return songs.size();
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    @Override
    public String toString() {
        return "Playlist name: " + name;
    }
}
