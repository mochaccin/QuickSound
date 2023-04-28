package com.quicksound;

import com.quicksound.songs.Player;
import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {

        AppController ac = AppController.getInstance();
        SongLibrary sl = SongLibrary.getInstance();

        sl.addSong(new Song("Rhapsody", "Toe", "Toe", "C:\\Users\\Mochaccin\\IdeaProjects\\QuickSound\\src\\main\\java\\Fight Song.wav", "The Beatles", 0));
        ac.displaySongs();
    }
}
