package com.quicksound;

import com.quicksound.songs.Player;
import com.quicksound.songs.Playlist;
import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;
import com.quicksound.user.User;
import com.quicksound.user.UserManager;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {

        SongLibrary.INSTANCE.addSong(new Song("Rhapsody", "Toe", "Goodbye", "src/main/java/Fight Song.wav", "5:08", 0));
        SongLibrary.INSTANCE.addSong(new Song("Sky of Twilight", "Kanako Itou", "Altier", "src/main/java/Sky of Twilight.wav", "6:21", 0));
        AppController.INSTANCE.displaySongs();
        AppController.INSTANCE.playSong(SongLibrary.INSTANCE.searchSongById(1));
        Player.INSTANCE.play();
        Player.INSTANCE.pause();
        Player.INSTANCE.resume();

    }
}
