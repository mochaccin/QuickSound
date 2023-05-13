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
            File file = new File("src/main/resources/songs/Fight Song.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            Logger.getLogger("SonarQube").log(Level.WARNING, e.getMessage());
        }
    }

    public void play(Song song) throws InterruptedException {
        loadSong(song);
        try {
            clip.start();
            System.out.println("Reproduciendo cancion: " + currentSong.getTitle());
            while (clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
                displayProgress();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Logger.getLogger("Logger").log(Level.WARNING, e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            Logger.getLogger("Logger").log(Level.WARNING, e.getMessage());
        }

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


    public void playPlaylist(Playlist playlist){

        try {
            int position = 1;
            for (Song song : playlist.getSongs()) {
                System.out.println("Reproduciendo cancion numero: " +position+" / " + playlist.getSize());
                play(song);
                while (clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
                    displayProgress();
                    Thread.sleep(1000);
                }
            }

        } catch (InterruptedException e){
            Logger.getLogger("Logger").log(Level.WARNING, e.getMessage());
            Thread.currentThread().interrupt();
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
            Logger.getLogger("Logger").log(Level.WARNING, e.getMessage());
        }
    }

    public void displayProgress() {
        long microsecondPosition = clip.getMicrosecondPosition();
        double secondPosition = (double) microsecondPosition / 1_000_000.0;
        long minutes = (long) (secondPosition / 60);
        long seconds = (long) (secondPosition % 60);
        System.out.printf("Cancion: %s | Posición actual: %02d:%02d / %s %n", currentSong.getTitle(), minutes, seconds, currentSong.getDuration());
    }

    public Clip getClip() {
        return clip;
    }
}
