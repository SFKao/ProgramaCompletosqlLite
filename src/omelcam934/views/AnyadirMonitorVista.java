package omelcam934.views;

import omelcam934.controlador.AnyadirMonitorController;
import omelcam934.controlador.MonitorController;

import javax.swing.*;

public class AnyadirMonitorVista {
    private JTextField id;
    private JLabel dniLabel;
    private JTextField dni;
    private JLabel nombreLabel;
    private JTextField nombre;
    private JLabel apellidosLabel;
    private JTextField apellidos;
    private JButton volver;
    private JButton anyadir;
    private JLabel dniInfo;
    private JPanel contenido;

    private JDialog frame;
    private AnyadirMonitorController controller;

    public void iniciar(JFrame parent, AnyadirMonitorController c){
        frame = new JDialog(parent,"Monitor", true);
        frame.setContentPane(contenido);
        this.controller = c;
        controller.setVista(this);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        colocarEscucha();

        frame.pack();
        frame.setVisible(true);
    }

    private void colocarEscucha(){
        volver.addActionListener(controller);
        anyadir.addActionListener(controller);
    }

    public JTextField getId() {
        return id;
    }

    public JTextField getDni() {
        return dni;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getApellidos() {
        return apellidos;
    }

    public JButton getVolver() {
        return volver;
    }

    public JButton getAnyadir() {
        return anyadir;
    }

    public JLabel getDniInfo() {
        return dniInfo;
    }

    public JDialog getFrame() {
        return frame;
    }
}
