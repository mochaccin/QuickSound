package com.quicksound.songs;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public enum Player {
    INSTANCE;

    private Song currentSong;
    private Clip clip;

    Player() {
        try {
            File file = new File("src/main/resources/songs/Sky of Twilight.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            System.out.println("Un error ha ocurrido: " + e.getMessage());
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
            System.out.println("Un error ha ocurrido: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("Un error ha ocurrido: " + e.getMessage());
        }

    }
    public long getPosition() {

        if (clip.isRunning()) {
            return clip.getMicrosecondPosition();
        } return 0;
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
                position++;
            }

        } catch (InterruptedException e){
            System.out.println("Un error ha ocurrido: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e){
            System.out.println("Un error ha ocurrido: " + e.getMessage());
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
            System.out.println("Un error ha ocurrido: " + e.getMessage());
        }
    }

    public void displayProgress() {

        if (clip.isActive()) {
            long microsecondPosition = clip.getMicrosecondPosition();
            double secondPosition = microsecondPosition / 1_000_000.0;
            long minutes = (long) (secondPosition / 60);
            long seconds = (long) (secondPosition % 60);
            System.out.printf("Cancion: %s | Posición actual: %02d:%02d / %s %n", currentSong.getTitle(), minutes, seconds, currentSong.getDuration());
        } else {
            System.out.println("Actualmente no hay ninguna cancion reproduciendoce.");
        }

    }

}
