package com.quicksound.songs;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

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
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void play() {
        clip.start();
        JOptionPane.showMessageDialog(null, "");
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

    public long getPosition() {
        return clip.getMicrosecondPosition();
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song song) {
        currentSong = song;
        try {
            File file = new File(currentSong.getFilePath());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.close();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
