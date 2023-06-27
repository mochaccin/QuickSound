package com.quicksound.guis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JButton menuCancionesButton;
    private JButton menuPlaylistsButton;
    private JButton configuracionDeLaCuentaButton;
    private JButton cerrarSesionButton;

    public MainWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        menuCancionesButton.addActionListener(this);
        menuPlaylistsButton.addActionListener(this);
        configuracionDeLaCuentaButton.addActionListener(this);
        cerrarSesionButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == cerrarSesionButton) {
            JOptionPane.showMessageDialog(this, "Sesion finalizada exitosamente.");
            setVisible(false);
            GuiManager.INSTANCE.getWelcomeWindow().setVisible(true);
        }

        if (event.getSource() == configuracionDeLaCuentaButton) {
            setVisible(false);
            GuiManager.INSTANCE.getAccountWindow().setVisible(true);
        }

        if (event.getSource() == menuCancionesButton) {
            setVisible(false);
            GuiManager.INSTANCE.getSongsWindow().setVisible(true);
        }

        if (event.getSource() == menuPlaylistsButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPlaylistsMenuWindow().setVisible(true);
        }

    }
}
