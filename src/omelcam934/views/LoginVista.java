package omelcam934.views;

import omelcam934.controlador.LoginController;
import omelcam934.controlador.PrincipalController;

import javax.swing.*;
import java.awt.*;

public class LoginVista extends JFrame{
    private JPasswordField contrasenya;
    private JButton logInButton;
    private JTextField usuario;
    private JLabel LblNombre;
    private JLabel LblPass;
    private JLabel LblAviso;
    private JPanel login;

    private LoginController controller;

    public void iniciar(){
        super.setTitle("Login");
        this.setContentPane(this.login);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void setController(LoginController c){
        this.controller = c;
        logInButton.addActionListener(controller);
    }

    public LoginVista(LoginController controller) throws HeadlessException {
        setController(controller);
    }

    public JPasswordField getContrasenya() {
        return contrasenya;
    }

    public JTextField getUsuario() {
        return usuario;
    }

    public JLabel getLblAviso() {
        return LblAviso;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public static void main(String[] args) {
        LoginController controller = new LoginController();
    }
}
