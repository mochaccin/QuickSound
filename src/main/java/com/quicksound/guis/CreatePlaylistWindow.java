package com.quicksound.guis;

import com.quicksound.services.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePlaylistWindow extends JFrame implements ActionListener {

    private JPanel createPlaylistPanel;
    private JTextField playlistName;
    private JButton createButton;
    private JButton backButton;

    public CreatePlaylistWindow (int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(createPlaylistPanel);
        playlistName.addActionListener(this);
        createButton.addActionListener(this);
        backButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPlaylistsMenuWindow().setVisible(true);
        }

        if (event.getSource() == createButton) {

            if (UserAuthentication.INSTANCE.isInputStringValid(playlistName.getText())) {
                UserAuthentication.INSTANCE.addPlaylist(playlistName.getText());
                JOptionPane.showMessageDialog(this,"Playlist creada exitosamente.");
                setVisible(false);
                GuiManager.INSTANCE.setHasPlaylists(true);
                GuiManager.INSTANCE.setUpGUIS();
                GuiManager.INSTANCE.getPlaylistsMenuWindow().setVisible(true);
            }
        }
    }
}
