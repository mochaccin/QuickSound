package com.quicksound.guis;

import com.quicksound.app.AppController;
import com.quicksound.services.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountWindow extends JFrame implements ActionListener {

    private JPanel accountPanel;
    private JButton changeUsernameButton;
    private JButton changePasswordButton;
    private JButton deleteAccountButton;
    private JButton backButton;

    public AccountWindow (int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(accountPanel);
        changePasswordButton.addActionListener(this);
        changeUsernameButton.addActionListener(this);
        deleteAccountButton.addActionListener(this);
        backButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == deleteAccountButton) {
            JOptionPane.showMessageDialog(this, "Cuenta eliminada exitosamente");
            UserManager.INSTANCE.deleteUser();
            setVisible(false);
            GuiManager.INSTANCE.getWelcomeWindow().setVisible(true);
        }

        if (event.getSource() == changeUsernameButton) {
            setVisible(false);
            GuiManager.INSTANCE.getUsernameWindow().setVisible(true);
        }

        if (event.getSource() == changePasswordButton) {
            setVisible(false);
            GuiManager.INSTANCE.getPasswordWindow().setVisible(true);
        }

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getMainWindow().setVisible(true);
        }
    }
}
