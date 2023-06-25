package com.quicksound.guis;

import com.quicksound.app.AppController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsernameWindow extends JFrame implements ActionListener {

    private JPanel usernamePanel;
    private JTextField username;
    private JButton changeUsernameButton;
    private JButton backButton;

    public UsernameWindow (int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(usernamePanel);
        username.addActionListener(this);
        changeUsernameButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == changeUsernameButton) {
            if (AppController.INSTANCE.isInputStringValid(username.getText())) {
                if (AppController.INSTANCE.changeUserUsername(username.getText())) {
                    JOptionPane.showMessageDialog(this, "Nombre cambiado exitosamente");
                    setVisible(false);
                    GuiManager.INSTANCE.getMainWindow().setVisible(true);
                } else {JOptionPane.showMessageDialog(this, "El nombre ingresado es el mismo");}
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
            }
        }

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getAccountWindow().setVisible(true);
        }
    }
}
