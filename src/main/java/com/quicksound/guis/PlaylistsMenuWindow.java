package com.quicksound.guis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistsMenuWindow extends JFrame implements ActionListener {

    private JPanel playlistsMenuPanel;
    private JButton escucharUnaPlaylistButton;
    private JButton crearUnaPlaylistButton;
    private JButton editarUnaPlaylistButton;
    private JButton eliminarUnaPlaylistButton;
    private JButton backButton;

    public PlaylistsMenuWindow (int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(playlistsMenuPanel);
        escucharUnaPlaylistButton.addActionListener(this);
        crearUnaPlaylistButton.addActionListener(this);
        editarUnaPlaylistButton.addActionListener(this);
        eliminarUnaPlaylistButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == escucharUnaPlaylistButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPlaylistsWindow().setVisible(true);
        }

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getMainWindow().setVisible(true);
        }

        if (event.getSource() == editarUnaPlaylistButton) {

        }

        if (event.getSource() == eliminarUnaPlaylistButton) {
            setVisible(false);
            GuiManager.INSTANCE.getDeletePlaylistWindow().setVisible(true);
        }

        if (event.getSource() == crearUnaPlaylistButton) {
            setVisible(false);
            GuiManager.INSTANCE.getCreatePlaylistWindow().setVisible(true);
        }

    }
}
