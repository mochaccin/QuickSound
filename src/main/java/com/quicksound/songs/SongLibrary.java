package com.quicksound.songs;

import java.util.ArrayList;
import java.util.List;

public enum SongLibrary {
    INSTANCE;
    private List<Song> songs = new ArrayList<>();

    public List<Song> searchSong(String query) {
        return songs.stream().filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                song.getArtist().toLowerCase().contains(query.toLowerCase()) ||
                song.getAlbum().toLowerCase().contains(query.toLowerCase())).toList();
    }

    public Song searchSongById(int id) {

        if (!songs.isEmpty()) {
            return songs.get(id);
        }

        System.out.println("No hay canciones en la libreria.");
        return null;
    }

    public int getSongLibrarySize() {
        return songs.size();
    }
    public void displaySongs() {

        if (!songs.isEmpty()) {
            System.out.println("Canciones disponibles en la biblioteca:");
            songs.forEach(song -> System.out.println("["+song.getId()+"]" + song));
        } else {
            System.out.println("No hay canciones en la libreria.");
        }
    }

    public void loadLibrary() {
        songs = SongLoader.INSTANCE.loadSongs("src/main/resources/songs/");
    }

    public void clearLibrary() {
        if (!songs.isEmpty()) {
            songs.clear();
        }
    }

    public boolean libraryIsEmpty(){
        return songs.isEmpty();
    }
}
