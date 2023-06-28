package com.quicksound.services;

import com.quicksound.models.Playlist;
import com.quicksound.models.Song;

import java.util.ArrayList;
import java.util.List;

public enum SongLibrary {
    INSTANCE;
    private List<Song> songs = new ArrayList<>();

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

    public String[] getSongsNames(){

        String[] names = new String[songs.size()];

        for (int i = 0; i < songs.size(); i++) {
            names[i] = songs.get(i).getTitle();
        }
        return names;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<Song> availableSongs(Playlist playlist) {
        return songs.stream().filter(song ->
                !playlist.getSongs().contains(song)
        ).toList();
    }
}
