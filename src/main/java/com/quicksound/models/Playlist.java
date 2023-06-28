package com.quicksound.models;

import com.quicksound.services.SongLibrary;

import javax.swing.*;
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
            songs.add(SongLibrary.INSTANCE.searchSongById(index));
            if (!songs.contains(song)) {
                songs.add(SongLibrary.INSTANCE.searchSongById(index));
                System.out.println("La cancion se ha agregado exitosamente a la playlist.");
            } else {
                JOptionPane.showMessageDialog(null ,"La cancion ya se encuentra en la playlist.");
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

    public int getSize() {
        return songs.size();
    }

    public void removeSongById(int id) {
        songs.remove(id);
    }

    @Override
    public String toString() {
        return "Playlist name: " + name;
    }
}
