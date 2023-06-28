package com.quicksound.app;

import com.quicksound.models.Playlist;
import com.quicksound.models.Song;
import com.quicksound.services.Player;
import com.quicksound.services.SongLibrary;
import com.quicksound.models.User;
import com.quicksound.services.UserAuthentication;
import com.quicksound.services.UserManager;

public enum AppController {
    INSTANCE;

    public void addSongToUserPlaylist(int playlistId, int id) {
        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();
        currentUser.getPlaylist(playlistId).addSongById(id);
    }

    public boolean isInputStringValid(String input){
        return input.matches("[a-zA-Z\\d]+");
    }
}
