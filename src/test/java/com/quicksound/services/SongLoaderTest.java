package com.quicksound.services;

import com.quicksound.models.Song;
import com.quicksound.services.SongLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongLoaderTest {

    SongLoader songLoader;

    @BeforeEach
    void setUp() {
        songLoader= SongLoader.INSTANCE;
    }

    @AfterEach
    void tearDown() {
        songLoader = null;
    }

    @Test
    @DisplayName("Verify that the songs are loaded correctly.")
    void loadSongs() {
        List<Song> songs = songLoader.loadSongs("src/main/resources/songs/");
        assertFalse(songs.isEmpty());
    }

    @Test
    @DisplayName("Verify that the function works if the directory doesnt exists.")
    void loadSongsIfDirectoryDoesntExists() {
        List<Song> songs = songLoader.loadSongs("src/main/resources/songs/");
        assertFalse(songs.isEmpty());
    }

    @Test
    @DisplayName("Verify that the song data is loaded correctly.")
    void getSongData() {
        List<Song> songs = songLoader.loadSongs("src/main/resources/songs/");
        String[] songData = songLoader.getSongData(songs.get(1).getFilePath());

        assertNotEquals(0, songData.length);
        assertEquals("Sky of Twilight", songData[0]);

    }

    @Test
    @DisplayName("Verify that the function works if the song doesnt exists.")
    void getSongDataIfSongDoesntExists() {
        String[] songData = songLoader.getSongData("/narnia/completo");
        assertEquals(0, songData.length);
        assertEquals("[]", Arrays.toString(songData));
    }

}