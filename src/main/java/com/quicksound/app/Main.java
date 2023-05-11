package com.quicksound.app;

import com.quicksound.app.AppController;
import com.quicksound.songs.Playlist;
import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        run();
    }

    public static void run() throws InterruptedException {
        AppController.INSTANCE.displayMenu();
    }
}
