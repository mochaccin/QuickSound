package com.quicksound;

import com.quicksound.songs.Player;
import com.quicksound.songs.Playlist;
import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;
import com.quicksound.user.User;

public enum AppController {
    INSTANCE;

    public void addSongToLibrary(Song song){
        SongLibrary.INSTANCE.addSong(song);
    }

    public void createPlaylist(User user, String name){
        user.addPlaylist(new Playlist(name));
    }

    public void playSong(Song song){
        Player.INSTANCE.setCurrentSong(song);
    }

    public void displaySongs(){
        SongLibrary.INSTANCE.displaySongs();
    }
}
