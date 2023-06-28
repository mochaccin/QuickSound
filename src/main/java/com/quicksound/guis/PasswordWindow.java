package com.quicksound.guis;

import com.quicksound.app.AppController;
import com.quicksound.services.UserAuthentication;
import com.quicksound.services.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordWindow extends JFrame implements ActionListener {

    private JPanel passwordPanel;
    private JButton backButton;
    private JTextField newPassword;
    private JButton changePasswordButton;

    public PasswordWindow (int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI (int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(passwordPanel);
        backButton.addActionListener(this);
        newPassword.addActionListener(this);
        changePasswordButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == changePasswordButton) {

            if (UserAuthentication.INSTANCE.isInputStringValid(newPassword.getText())) {
                if (UserManager.INSTANCE.changeUserPassword(newPassword.getText())) {
                    JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente");
                    setVisible(false);
                    GuiManager.INSTANCE.getMainWindow().setVisible(true);
                } else {JOptionPane.showMessageDialog(this, "La contraseña introducida es la misma");}
            } else {JOptionPane.showMessageDialog(this, "Por favor introduzca una contraseña valida");}
        }

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getAccountWindow().setVisible(true);
        }

    }
}
