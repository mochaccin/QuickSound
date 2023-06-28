package com.quicksound.guis;

import com.quicksound.app.AppController;
import com.quicksound.services.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame implements ActionListener {
    private JPanel registerPanel;
    private JTextField username;
    private JTextField password;
    private JButton registerButton;
    private JButton backButton;

    public RegisterWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(registerPanel);
        username.addActionListener(this);
        password.addActionListener(this);
        registerButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == registerButton) {

            boolean validName = AppController.INSTANCE.isInputStringValid(username.getText());
            boolean validPassword = AppController.INSTANCE.isInputStringValid(password.getText());
            if(validName && validPassword){
                UserManager.INSTANCE.registerUser(username.getText(), password.getText());
                JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
                setVisible(false);
                GuiManager.INSTANCE.getWelcomeWindow().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor introduzca valores validos");
            }
        }

        if (event.getSource() == backButton) {
            setVisible(false);
            GuiManager.INSTANCE.getWelcomeWindow().setVisible(true);
        }
    }
}
