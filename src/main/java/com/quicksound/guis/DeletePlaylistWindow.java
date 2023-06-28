package com.quicksound.guis;

import com.quicksound.models.Playlist;
import com.quicksound.user.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePlaylistWindow extends JFrame implements ActionListener {
    private JPanel deletePlaylistPanel;
    private JList playlists;
    private JButton eliminarButton;
    private JButton backButton;

    private DefaultListModel<Playlist> model = new DefaultListModel<>();

    public DeletePlaylistWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(deletePlaylistPanel);
        eliminarButton.addActionListener(this);
        backButton.addActionListener(this);
        playlists.setModel(model);
        assert UserAuthentication.INSTANCE.getCurrentUser() != null;
        model.addAll(UserAuthentication.INSTANCE.getCurrentUser().getUserPlaylists());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == eliminarButton) {

            if (!playlists.isSelectionEmpty()) {
                assert UserAuthentication.INSTANCE.getCurrentUser() != null;
                UserAuthentication.INSTANCE.getCurrentUser().removePlaylistByIndex(playlists.getSelectedIndex());
                model.remove(playlists.getSelectedIndex());
                JOptionPane.showMessageDialog(this, "Playlist eliminada con exito.");
            }
        }

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPlaylistsMenuWindow().setVisible(true);
        }
    }
}
