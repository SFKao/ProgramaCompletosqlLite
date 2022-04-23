package omelcam934.views;

import omelcam934.controlador.MonitorController;
import omelcam934.controlador.VisitaController;

import javax.swing.*;
import java.awt.*;

public class VisitaVista {
    private VisitaController controller;

    private JPanel visita;
    private JLabel idLabel;
    private JTextField id;
    private JLabel fechaLabel;
    private JTextField fecha;
    private JLabel duracionLabel;
    private JTextField duracion;
    private JLabel numeroSalaLabel;
    private JTextField numeroSala;
    private JCheckBox editarBox;
    private JButton volver;
    private JButton actualizar;
    private JButton borrar;
    private JLabel monitorLabel;
    private JLabel visitasLabel;
    private JLabel dniLabel;
    private JTextField dni;
    private JLabel nombreLabel;
    private JTextField nombre;
    private JLabel apellidosLabel;
    private JTextField apellidos;
    private JLabel idMonitorLabel;
    private JTextField idMonitor;
    private JButton cambiar;
    private JDialog frame;

    public void iniciar(JFrame parent, VisitaController c){
        frame = new JDialog(parent, "Visita",true);
        frame.setContentPane(this.visita);
        this.controller = c;
        controller.setVista(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        actualizar.setEnabled(false);
        borrar.setEnabled(false);
        cambiar.setEnabled(false);

        colocarEscucha();
        llenarDatos();
        frame.pack();
        frame.setVisible(true);
    }


    private void colocarEscucha(){
        volver.addActionListener(controller);
        actualizar.addActionListener(controller);
        borrar.addActionListener(controller);
        cambiar.addActionListener(controller);

        editarBox.addActionListener(controller);
    }

    private void llenarDatos(){
        controller.llenarDatos();
    }

    public JTextField getId() {
        return id;
    }

    public JTextField getFecha() {
        return fecha;
    }

    public JTextField getDuracion() {
        return duracion;
    }

    public JTextField getNumeroSala() {
        return numeroSala;
    }

    public JCheckBox getEditarBox() {
        return editarBox;
    }

    public JButton getVolver() {
        return volver;
    }

    public JButton getActualizar() {
        return actualizar;
    }

    public JButton getBorrar() {
        return borrar;
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

    public JTextField getIdMonitor() {
        return idMonitor;
    }

    public JButton getCambiar() {
        return cambiar;
    }

    public JDialog getFrame() {
        return frame;
    }
}
