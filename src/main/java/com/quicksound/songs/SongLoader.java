package com.quicksound.songs;

import com.quicksound.models.Song;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

public enum SongLoader {
    INSTANCE;

    public List<Song> loadSongs(String directoryPath) {

        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".wav"));

        ArrayList<Song> songs = new ArrayList<>();
        int id = 0;
        if (files != null) {
            for (File file : files) {
                try {
                    String[] songData = SongLoader.INSTANCE.getSongData(file.getPath());
                    Song song = new Song(songData[0], songData[1], songData[2], songData[3], songData[4], songData[5], id);
                    songs.add(song);
                    id++;
                } catch (Exception ex) {
                    System.out.println("Error loading song: " + ex.getMessage());
                }
            }
            return songs;
        } else {
            System.out.println("No songs found.");
            return songs;
        }
    }

    public String[] getSongData(String filePath) {

        File file = new File(filePath);

        if (file.exists()){
            LogManager.getLogManager().reset();

            try {
                AudioFile audioFile = AudioFileIO.read(file);

                Tag tag = audioFile.getTag();
                String title = tag.getFirst(FieldKey.TITLE);
                String artist = tag.getFirst(FieldKey.ARTIST);
                String album = tag.getFirst(FieldKey.ALBUM);
                String genre = tag.getFirst(FieldKey.GENRE);
                int duration = audioFile.getAudioHeader().getTrackLength();

                long minutes = duration / 60;
                long seconds = duration % 60;

                String length = String.format("Duración: %02d:%02d", minutes, seconds);

                return new String[]{title, artist, album, genre, filePath, length};

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String[0];
            }

        }

        System.out.println("Song not found.");
        return new String[0];
    }
}
