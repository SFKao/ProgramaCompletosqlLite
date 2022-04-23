package omelcam934.views;

import omelcam934.controlador.MonitorController;
import omelcam934.controlador.VincularVisitaMonitorController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class VincularVisitaMonitorVista{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable monitores;
    private DefaultTableModel model;
    private JDialog frame;

    private VincularVisitaMonitorController controller;

    public VincularVisitaMonitorVista() {

    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JDialog getFrame() {
        return frame;
    }

    public JTable getMonitores() {
        return monitores;
    }

    public void iniciar(JDialog parent, VincularVisitaMonitorController c){
        frame = new JDialog(parent,"Monitor", true);
        frame.setContentPane(this.contentPane);
        this.controller = c;
        controller.setVista(this);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        llenarTabla();
        colocarEscucha();
        frame.pack();
        frame.setVisible(true);
    }

    private void llenarTabla(){
        String [] aux = new String[]{"Id", "DNI", "nombre", "apellidos"};
        model = new DefaultTableModel(null,aux);
        this.monitores.setDefaultEditor(Object.class, null);
        controller.llenarTabla(model);
        this.monitores.setModel(model);
    }

    private void colocarEscucha(){
        this.monitores.addMouseListener(controller);
        this.buttonCancel.addActionListener(controller);
        this.buttonOK.addActionListener(controller);
    }


}
