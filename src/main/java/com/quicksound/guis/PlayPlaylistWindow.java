package com.quicksound.guis;

import com.quicksound.app.AppController;
import com.quicksound.services.Player;
import com.quicksound.models.Song;
import com.quicksound.services.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class PlayPlaylistWindow extends JFrame implements ActionListener {

    private JPanel playPlaylistPanel;
    private JList songs;
    private JButton reproducirButton;
    private JButton backButton;
    private JButton pausarButton;
    private JButton loopButton;
    private JButton randomizarButton;

    private JButton _lastButtonPressed;
    long position = 0;

    private List<Song> playlist = UserAuthentication.INSTANCE.getCurrentUser().getPlaylist(GuiManager.INSTANCE.getPlaylistIndex()).getSongs();
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
        model.addAll(playlist);
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

        if (event.getSource() == reproducirButton) {

            if (!songs.isSelectionEmpty()) {

                if (!Player.INSTANCE.isBusy()){
                    try {
                        Player.INSTANCE.play(playlist.get(songs.getSelectedIndex()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        Player.INSTANCE.stop();
                        Player.INSTANCE.play(playlist.get(songs.getSelectedIndex()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            _lastButtonPressed = reproducirButton;
            pausarButton.setText("Pausar");
        }

        if (event.getSource() == pausarButton) {

            if (_lastButtonPressed != pausarButton) {
                pausarButton.setText("Pausar");
            }

            if (_lastButtonPressed == pausarButton && pausarButton.getText().equals("Reanudar")) {
                pausarButton.setText("Pausar");
                Player.INSTANCE.resume(position);
            } else if (Player.INSTANCE.isBusy()) {
                position = Player.INSTANCE.getPosition();
                Player.INSTANCE.stop();
                pausarButton.setText("Reanudar");
                _lastButtonPressed = pausarButton;
            } else if (!Player.INSTANCE.isBusy()) {
                position = 0;
                Player.INSTANCE.clearPlayer();
            }
        }

        if (event.getSource() == loopButton) {
            Player.INSTANCE.loop();
            _lastButtonPressed = loopButton;
            pausarButton.setText("Pausar");
        }

        if (event.getSource() == randomizarButton) {

            if (Player.INSTANCE.isBusy()){
                Player.INSTANCE.pause();
                Random random = new Random();
                try {
                    Player.INSTANCE.play(playlist.get(random.nextInt(0, playlist.size())));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Random random = new Random();
                try {
                    Player.INSTANCE.play(playlist.get(random.nextInt(0, playlist.size())));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            pausarButton.setText("Pausar");
            _lastButtonPressed = randomizarButton;
        }

    }
}
