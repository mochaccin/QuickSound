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

    public void addSongById(int index) {

        if (!SongLibrary.INSTANCE.libraryIsEmpty()) {
            Song song = SongLibrary.INSTANCE.searchSongById(index);
            if (!songs.contains(song)) {
                songs.add(SongLibrary.INSTANCE.searchSongById(index));
                System.out.println("La cancion se ha agregado exitosamente a la playlist.");
            } else {
                System.out.println("La cancion ya se encuentra en la playlist.");
            }
        } else {
            System.out.println("La libreria se encuentra vacia.");
        }
    }
    public List<Song> getSongs() {
        return songs;
    }

    public Song getSongById(int index) {

        if (!songs.isEmpty()) {
            return songs.get(index);
        } else {
            System.out.println("La playlist se encuentra vacia.");
            return null;
        }
    }
    public String getName() {
        return name;
    }

    public void changePlaylistName(String newName) {

        if (!name.equals(newName)) {
            name = newName;
            System.out.println("Nombre de la playlist ha sido cambiado exitosamente.");
        } else {
            System.out.println("El nombre seleccionado corresponde al nombre actual de la playlist.");
        }
    }

    public void displaySongs(){

        if (!songs.isEmpty()) {
            songs.forEach(song -> System.out.println("["+songs.indexOf(song)+"]" + song.toString()));
        } else {
            System.out.println("La playlist se encuentra vacia.");
        }
    }

    public int getSize() {
        return songs.size();
    }

    public void removeSong(Song song) {

        if (songs.contains(song)) {
            songs.remove(song);
        } else {
            System.out.println("La cancion deseada no se encuentra en la playlist.");
        }
    }

    @Override
    public String toString() {
        return "Playlist name: " + name;
    }
}
