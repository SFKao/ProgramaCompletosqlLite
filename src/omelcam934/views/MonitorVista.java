package omelcam934.views;

import omelcam934.controlador.MonitorController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MonitorVista {

    private MonitorController controller;

    private JPanel monitor;
    private JTextField id;
    private JTextField dni;
    private JTextField nombre;
    private JTextField apellidos;
    private JLabel idLabel;
    private JLabel dniLabel;
    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel visitasLabel;
    private JLabel monitorLabel;
    private JTable tablaVisitas;
    private JCheckBox editarBox;
    private JButton actualizar;
    private JButton borrar;
    private JButton volver;
    private JScrollPane scrollPaneTabla;
    private DefaultTableModel datosTablaVisita;

    private JDialog frame;

    public void iniciar(JFrame parent, MonitorController c){
        frame = new JDialog(parent,"Monitor", true);
        frame.setContentPane(this.monitor);
        this.controller = c;
        controller.setVista(this);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        actualizar.setEnabled(false);
        borrar.setEnabled(false);

        llenarTabla();
        colocarEscucha();
        llenarDatos();

        if(!controller.isAdmin()){
            editarBox.setVisible(false);
        }

        frame.pack();
        frame.setVisible(true);
    }


    public void llenarTabla(){

        String [] columnas = {"id", "fecha", "duracion", "numero de sala"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tablaVisitas.setDefaultEditor(Object.class, null);
        controller.llenarTabla(modelo);
        this.tablaVisitas.setModel(modelo);

    }



    public void colocarEscucha(){
        actualizar.addActionListener(controller);
        borrar.addActionListener(controller);
        volver.addActionListener(controller);
        editarBox.addActionListener(controller);
    }


    public void llenarDatos(){
        controller.llenarDatos();
    }

    public void setId(int id){
        this.id.setText(String.valueOf(id));
    }

    public void setDNI(String dni){
        this.dni.setText(dni);
    }

    public void setNombre(String nombre){
        this.nombre.setText(nombre);
    }

    public void setApellidos(String apellidos){
        this.apellidos.setText(apellidos);
    }

    public JCheckBox getEditarBox() {
        return editarBox;
    }

    public JButton getActualizar() {
        return actualizar;
    }

    public JButton getBorrar() {
        return borrar;
    }

    public JButton getVolver() {
        return volver;
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

    public JDialog getFrame() {
        return frame;
    }
}
