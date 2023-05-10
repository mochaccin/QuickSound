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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {

         StringBuilder str = new StringBuilder("Playlist: " +
                 name + " Songs: \n");

        for (Song song : songs) {
            str.append(song.toString());
        }
        return str.toString();
    }
}
