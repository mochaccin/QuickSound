package com.quicksound;

import com.quicksound.songs.Playlist;
import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;

public class AppController {
    private static AppController instance = null;

    private AppController() {}

    public static AppController getInstance() {
        AppController result = instance;
        if (result == null) {
            synchronized (AppController.class) {
                if (result == null) {
                    instance = new AppController();
                }
            }
        }
        return instance;
    }

    public void addSongToLibrary(Song song){}

    public void createPlaylist(Playlist playlist){}

    public void playSong(Song song){}

    public void displaySongs(){
        SongLibrary.getInstance().displaySongs();
    }
}