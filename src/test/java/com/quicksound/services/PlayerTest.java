package com.quicksound.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;
    SongLibrary songLibrary;
    @BeforeEach
    void setUp() {
        player = Player.INSTANCE;
        songLibrary = SongLibrary.INSTANCE;
        songLibrary.loadLibrary();
    }

    @AfterEach
    void tearDown() {
        player.clearPlayer();
        player = null;
        songLibrary = null;
    }

    @Test
    void play() throws InterruptedException {
        assertFalse(player.isBusy());
        player.play(songLibrary.searchSongById(0));
        JOptionPane.showMessageDialog(null,"PlayTest");
        assertTrue(player.isBusy());
    }

    @Test
    void stop() throws InterruptedException {
        player.play(songLibrary.searchSongById(0));
        player.stop();
        assertFalse(player.isBusy());
        JOptionPane.showMessageDialog(null,"StopTest");
    }

    @Test
    void pause() throws InterruptedException {
        long position = 0;
        assertEquals(0, position);
        player.play(songLibrary.searchSongById(0));
        Thread.sleep(2000);
        position = player.pause();
        assertNotEquals(0, position);
        JOptionPane.showMessageDialog(null,"PauseTest");
    }

    @Test
    void resume() throws InterruptedException {

        long position = 0;
        assertEquals(0, position);
        player.play(songLibrary.searchSongById(0));
        Thread.sleep(2000);
        position = player.pause();
        player.resume(position);
        assertNotEquals(0, player.getPosition());
        JOptionPane.showMessageDialog(null,"PauseTest");

    }

    @Test
    void clearPlayer() throws InterruptedException {
        player.play(songLibrary.searchSongById(0));
        player.clearPlayer();
        assertFalse(player.isBusy());
        JOptionPane.showMessageDialog(null, "clearPlayerTest");
    }

    @Test
    void isBusy() throws InterruptedException {
        assertFalse(player.isBusy());
        player.play(songLibrary.searchSongById(0));
        JOptionPane.showMessageDialog(null,"isBusyTest");
        assertTrue(player.isBusy());
    }
}