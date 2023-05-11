package com.quicksound.songs;

import java.util.ArrayList;
import java.util.List;

public enum SongLibrary {
    INSTANCE;
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public List<Song> searchSong(String query) {
        return songs.stream().filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                song.getArtist().toLowerCase().contains(query.toLowerCase()) ||
                song.getAlbum().toLowerCase().contains(query.toLowerCase())).toList();
    }

    public Song searchSongById(int id) {
        return songs.get(id);
    }

    public int getSongLibrarySize() {
        return songs.size();
    }
    public void displaySongs() {
        System.out.println("Canciones disponibles en la biblioteca:");
        songs.forEach(song -> System.out.println("["+song.getId()+"]" + song.toString()));
    }

    public void loadLibrary() {
        songs = SongLoader.INSTANCE.loadSongs("src/main/resources/songs/");
    }
}
