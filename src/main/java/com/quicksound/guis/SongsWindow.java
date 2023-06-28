package com.quicksound.guis;

import com.quicksound.app.AppController;
import com.quicksound.songs.Player;
import com.quicksound.models.Song;
import com.quicksound.songs.SongLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SongsWindow extends JFrame implements ActionListener {
    private JPanel songsPanel;
    private JList canciones;
    private JButton reproducirButton;
    private JButton randomizarButton;
    private JButton detenerButton;
    private JButton loopButton;
    private JButton backButton;
    private JButton _lastButtonPressed;
    long position = 0;
    private DefaultListModel<Song> model = new DefaultListModel<>();

    public SongsWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(songsPanel);
        canciones.setModel(model);
        model.addAll(SongLibrary.INSTANCE.getSongs());
        reproducirButton.addActionListener(this);
        randomizarButton.addActionListener(this);
        detenerButton.addActionListener(this);
        loopButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == reproducirButton) {

            if (!canciones.isSelectionEmpty()) {

                if (!Player.INSTANCE.isBusy()){
                    try {
                        AppController.INSTANCE.playSong(AppController.INSTANCE.searchSongById(canciones.getSelectedIndex()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        Player.INSTANCE.stop();
                        AppController.INSTANCE.playSong(AppController.INSTANCE.searchSongById(canciones.getSelectedIndex()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            _lastButtonPressed = reproducirButton;
            detenerButton.setText("Pausar");
        }

        if (event.getSource() == detenerButton) {

            if (_lastButtonPressed != detenerButton) {
                detenerButton.setText("Pausar");
            }

            if (_lastButtonPressed == detenerButton && detenerButton.getText().equals("Reanudar")) {
                detenerButton.setText("Pausar");
                Player.INSTANCE.resume(position);
            } else if (Player.INSTANCE.isBusy()) {
                position = Player.INSTANCE.getPosition();
                Player.INSTANCE.stop();
                detenerButton.setText("Reanudar");
                _lastButtonPressed = detenerButton;
            } else if (!Player.INSTANCE.isBusy()) {
                position = 0;
                Player.INSTANCE.clearPlayer();
            }
        }

        if (event.getSource() == loopButton) {
            Player.INSTANCE.loop();
            _lastButtonPressed = loopButton;
            detenerButton.setText("Pausar");
        }

        if (event.getSource() == randomizarButton) {

            if (Player.INSTANCE.isBusy()){
                Player.INSTANCE.pause();
                Random random = new Random();
                try {
                    AppController.INSTANCE.playSong(AppController.INSTANCE.searchSongById(random.nextInt(0, AppController.INSTANCE.getLibrarySize())));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Random random = new Random();
                try {
                    AppController.INSTANCE.playSong(AppController.INSTANCE.searchSongById(random.nextInt(0, AppController.INSTANCE.getLibrarySize())));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            detenerButton.setText("Pausar");
            _lastButtonPressed = randomizarButton;
        }

        if (event.getSource() == backButton) {
            Player.INSTANCE.clearPlayer();
            setVisible(false);
            GuiManager.INSTANCE.getMainWindow().setVisible(true);
        }
    }
}
