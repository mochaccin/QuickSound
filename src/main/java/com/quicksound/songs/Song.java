package com.quicksound.songs;

public class Song {

    private String title;
    private String artist;
    private String album;

    private String filePath;
    private String duration;
    private String genre;
    private int id;

    public Song(String title, String artist, String album, String genre, String filePath, String duration, int id) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.filePath = filePath;
        this.duration = duration;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cancion: " +
                " " + title + " | Artista: " + artist + " |" +
                " Album: " + album + " |" +
                " Duracion: " + duration + " ";
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
