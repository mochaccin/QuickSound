package com.quicksound.songs;

import com.quicksound.AppController;
import com.quicksound.user.UserManager;

import java.util.ArrayList;
import java.util.List;

public class SongLibrary {
    private static SongLibrary instance = null;
    private List<Song> songs;

    private SongLibrary() {
        songs = new ArrayList<Song>();
    }

    public static SongLibrary getInstance() {
        SongLibrary result = instance;
        if (result == null) {
            synchronized (SongLibrary.class) {
                if (result == null) {
                    instance = new SongLibrary();
                }
            }
        }
        return instance;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public List<Song> searchSong(String query) {
        List<Song> results = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    song.getArtist().toLowerCase().contains(query.toLowerCase()) ||
                    song.getAlbum().toLowerCase().contains(query.toLowerCase())) {
                results.add(song);
            }
        }
        return results;
    }

    public Song searchSongById(int id) {
        return songs.get(id);
    }

    public void displaySongs() {
        System.out.println("Canciones disponibles: ");
        for (Song song : songs) {
            System.out.println(song.toString());
        }
    }


}