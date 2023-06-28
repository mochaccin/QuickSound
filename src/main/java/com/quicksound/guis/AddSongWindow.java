package com.quicksound.guis;

import com.quicksound.songs.Song;
import com.quicksound.songs.SongLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSongWindow extends JFrame implements ActionListener {

    private JPanel addSongWindowPanel;
    private JList songs;
    private JButton addSongButton;
    private JButton backButton;
    private DefaultListModel<Song> model = new DefaultListModel<>();

    public AddSongWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(addSongWindowPanel);
        addSongButton.addActionListener(this);
        backButton.addActionListener(this);
        songs.setModel(model);
        model.addAll(SongLibrary.INSTANCE.availableSongs(GuiManager.INSTANCE.getPlaylistIndex()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
