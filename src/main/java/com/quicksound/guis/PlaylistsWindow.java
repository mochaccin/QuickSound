package com.quicksound.guis;

import com.quicksound.models.Playlist;
import com.quicksound.user.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistsWindow extends JFrame implements ActionListener {

    private JPanel playlistsPanel;
    private JList playlists;
    private JButton reproducirButton;
    private JButton backButton;

    private DefaultListModel<Playlist> model = new DefaultListModel<>();

    public PlaylistsWindow (int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(playlistsPanel);
        playlists.setModel(model);
        model.addAll(UserAuthentication.INSTANCE.getCurrentUser().getUserPlaylists());
        reproducirButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPlaylistsMenuWindow().setVisible(true);
        }

        if (event.getSource() == reproducirButton) {

            if (!playlists.isSelectionEmpty()) {
                setVisible(false);
                GuiManager.INSTANCE.enablePlayPlaylists(playlists.getSelectedIndex());
            }
        }
    }
}
