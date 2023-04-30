package com.quicksound.songs;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum Player {
    INSTANCE;

    private Song currentSong;
    private Clip clip;
    private Player() {
        try {
            File file = new File("src/main/java/Fight Song.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            Logger.getLogger("SonarQube").log(Level.WARNING, e.getMessage());
        }
    }

    public void play(Song song) {
        loadSong(song);
        clip.start();
    }

    public void pause() {
        clip.stop();
        JOptionPane.showMessageDialog(null, "");
    }

    public void resume() {
        jump(getPosition());
        clip.start();
        JOptionPane.showMessageDialog(null, "");
    }

    public void jump(long position) {
        clip.setMicrosecondPosition(position);
    }
    public void stop() {
        clip.stop();
        clip.close();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public long getPosition() {
        return clip.getMicrosecondPosition();
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void playPlaylist(Playlist playlist){

        try {

            for (Song song : playlist.getSongs()) {
                play(song);
                while (clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
                }
            }

        } catch (Exception e){
            Logger.getLogger("Logger").log(Level.WARNING, e.getMessage());
        }

    }

    public void loadSong(Song song) {
        currentSong = song;
        try {
            File file = new File(currentSong.getFilePath());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.close();
            clip.open(audioInputStream);
        } catch (Exception e) {
            Logger.getLogger("Nico").log(Level.WARNING, e.getMessage());
        }
    }
}
