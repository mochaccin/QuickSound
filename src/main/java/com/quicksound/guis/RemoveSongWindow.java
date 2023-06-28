package com.quicksound.guis;

import com.quicksound.models.Song;
import com.quicksound.services.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveSongWindow extends JFrame implements ActionListener {

    private JList songs;
    private JButton removeSongButton;
    private JButton backButton;
    private DefaultListModel<Song> model = new DefaultListModel<>();
    private JPanel removeSongPanel;
    public RemoveSongWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(removeSongPanel);
        removeSongButton.addActionListener(this);
        backButton.addActionListener(this);
        songs.setModel(model);
        model.addAll(UserAuthentication.INSTANCE.getCurrentUser()
                .getPlaylist(GuiManager.INSTANCE.getPlaylistIndex()).getSongs());
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.editPlaylistMenuWindow.setVisible(true);
        }

        if (event.getSource() == removeSongButton) {

            if (!songs.isSelectionEmpty()) {
                UserAuthentication.INSTANCE.getCurrentUser().getPlaylist(
                        GuiManager.INSTANCE.getPlaylistIndex()).removeSongById(songs.getSelectedIndex()
                );
                model.remove(songs.getSelectedIndex());
                GuiManager.INSTANCE.setUpGUIS();
                JOptionPane.showMessageDialog(this, "Cancion eliminada exitosamente.");
            }
        }
    }
}
