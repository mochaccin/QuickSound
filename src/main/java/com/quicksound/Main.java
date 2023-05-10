package com.quicksound;

import com.quicksound.songs.Playlist;
import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;
import com.quicksound.user.UserAuthentication;
import com.quicksound.user.UserManager;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {

        SongLibrary songLibrary = SongLibrary.INSTANCE;
        AppController appController = AppController.INSTANCE;

        songLibrary.addSong(new Song("Rhapsody", "Toe", "Goodbye", "src/main/java/Fight Song.wav", "5:08", 0));
        songLibrary.addSong(new Song("Sky of Twilight", "Kanako Itou", "Altier", "src/main/java/Sky of Twilight.wav", "6:21", 1));
        songLibrary.addSong(new Song("yipi", "tdh creature", "tdh", "src/main/java/yipi.wav", "0:02", 2));

        Playlist playlist = new Playlist("My Playlist");
        playlist.addSong(songLibrary.searchSongById(2));
        playlist.addSong(songLibrary.searchSongById(1));
        appController.displayMenu();
    }
}
