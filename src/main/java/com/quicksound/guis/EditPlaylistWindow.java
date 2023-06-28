package com.quicksound.guis;

import com.quicksound.models.Playlist;
import com.quicksound.services.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPlaylistWindow extends JFrame implements ActionListener {

    private JPanel editPlaylistPanel;
    private JList playlists;
    private JButton editButton;
    private JButton backButton;

    private DefaultListModel<Playlist> model = new DefaultListModel<>();

    public EditPlaylistWindow(int width, int height) {
        setUpGUI(width, height);
    }
    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(editPlaylistPanel);
        editButton.addActionListener(this);
        playlists.setModel(model);
        model.addAll(UserAuthentication.INSTANCE.getCurrentUser().getUserPlaylists());
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPlaylistsMenuWindow().setVisible(true);
        }

        if (event.getSource() == editButton) {
            setVisible(false);
            GuiManager.INSTANCE.setPlaylistIndex(playlists.getSelectedIndex());
            GuiManager.INSTANCE.getEditPlaylistMenuWindow().setVisible(true);
        }

    }
}
