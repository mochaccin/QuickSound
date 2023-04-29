package com.quicksound;

import com.quicksound.songs.Playlist;
import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        SongLibrary.INSTANCE.addSong(new Song("Rhapsody", "Toe", "Goodbye", "src/main/java/Fight Song.wav", "5:08", 0));
        SongLibrary.INSTANCE.addSong(new Song("Sky of Twilight", "Kanako Itou", "Altier", "src/main/java/Sky of Twilight.wav", "6:21", 1));
        SongLibrary.INSTANCE.addSong(new Song("yipi", "tdh creature", "tdh", "src/main/java/yipi.wav", "0:02", 2));

        Playlist playlist = new Playlist("My Playlist");
        playlist.addSong(SongLibrary.INSTANCE.searchSongById(2));
        playlist.addSong(SongLibrary.INSTANCE.searchSongById(1));
        AppController.INSTANCE.displaySongs();
        AppController.INSTANCE.playPlaylist(playlist);
        AppController.INSTANCE.stopSong();
    }
}
