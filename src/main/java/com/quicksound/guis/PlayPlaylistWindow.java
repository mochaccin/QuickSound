package com.quicksound.guis;

import com.quicksound.songs.Playlist;
import com.quicksound.songs.Song;
import com.quicksound.user.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPlaylistWindow extends JFrame implements ActionListener {

    private JPanel playPlaylistPanel;
    private JList songs;
    private JButton reproducirButton;
    private JButton backButton;
    private JButton pausarButton;
    private JButton loopButton;
    private JButton randomizarButton;
    private DefaultListModel<Song> model = new DefaultListModel<>();

    public PlayPlaylistWindow (int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        songs.setModel(model);
        model.addAll(UserAuthentication.INSTANCE.getCurrentUser()
                .getPlaylist(GuiManager.INSTANCE.getPlaylistIndex()).getSongs()
        );
        reproducirButton.addActionListener(this);
        backButton.addActionListener(this);
        pausarButton.addActionListener(this);
        loopButton.addActionListener(this);
        randomizarButton.addActionListener(this);
        setContentPane(playPlaylistPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPlaylistsWindow().setVisible(true);
        }

    }
}
