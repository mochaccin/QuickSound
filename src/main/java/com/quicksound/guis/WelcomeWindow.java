package com.quicksound.guis;

import com.quicksound.app.AppController;
import com.quicksound.services.UserAuthentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame implements ActionListener {

    JButton logInButton;
    JTextField password;
    JTextField username;
    JPanel mainPanel;
    private JButton createAnAccountButton;

    public WelcomeWindow(int width, int height) {
        setUpGUI(width, height);
    }

    private void setUpGUI(int width, int height) {
        setTitle("Quicksound");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        username.addActionListener(this);
        password.addActionListener(this);
        logInButton.addActionListener(this);
        createAnAccountButton.addActionListener(this);
    }

    // registrar usuarios
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == logInButton) {

            boolean validName = UserAuthentication.INSTANCE.isInputStringValid(username.getText());
            boolean validPassword = UserAuthentication.INSTANCE.isInputStringValid(password.getText());
            if(validName && validPassword){
                if (UserAuthentication.INSTANCE.login(username.getText(), password.getText())) {
                    JOptionPane.showMessageDialog(this, "Sesion iniciada exitosamente");
                    setVisible(false);
                    GuiManager.INSTANCE.login();
                } else { JOptionPane.showMessageDialog(this, "La cuenta no existe"); }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor introduzca valores validos");
            }
        }

        if (event.getSource() == createAnAccountButton) {
            setVisible(false);
            GuiManager.INSTANCE.getRegisterWindow().setVisible(true);
        }
    }
}
