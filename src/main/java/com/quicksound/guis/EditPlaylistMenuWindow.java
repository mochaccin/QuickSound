package com.quicksound.guis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPlaylistMenuWindow extends JFrame implements ActionListener {

    private JPanel editPlaylistMenuPanel;
    private JButton addASongButton;
    private JButton deleteASongButton;
    private JButton changePlaylistNameButton;
    private JButton deletePlaylistButton;
    private JButton backButton;

    public EditPlaylistMenuWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(editPlaylistMenuPanel);
        addASongButton.addActionListener(this);
        deleteASongButton.addActionListener(this);
        changePlaylistNameButton.addActionListener(this);
        deletePlaylistButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.playlistsMenuWindow.setVisible(true);
        }

        if (event.getSource() == addASongButton) {
            setVisible(false);
            GuiManager.INSTANCE.getAddSongWindow().setVisible(true);
        }
    }
}
