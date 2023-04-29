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

    public void playPlaylist(Playlist playlist){
        Player.INSTANCE.playPlaylist(playlist);
    }

    public void playSong(Song song){
        Player.INSTANCE.play(song);
    }
    public void pauseSong(){
        Player.INSTANCE.pause();
    }
    public void resumeSong(){
        Player.INSTANCE.resume();
    }
    public void stopSong(){
        Player.INSTANCE.stop();
    }

    public void displaySongs(){
        SongLibrary.INSTANCE.displaySongs();
    }
}
