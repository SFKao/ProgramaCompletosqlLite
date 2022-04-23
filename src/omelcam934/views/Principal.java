package omelcam934.views;

import omelcam934.controlador.PrincipalController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Principal {

    private PrincipalController controller;
    private JLabel monitoresLabel;
    private JLabel visitasLabel;
    private JTable visitaTable;
    private JPanel Principal;
    private JScrollPane visitaPane;
    private JScrollPane monitorPane;
    private JTable monitorTable;
    private JButton crearMonitor;
    private JButton crearVisita;


    private JFrame frame;

    public void iniciar(PrincipalController c){
        frame = new JFrame("MonitorVista");
        frame.setContentPane(this.Principal);
        this.controller = c;
        controller.setVista(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        llenarTabla();
        colocarEscucha();
        frame.pack();
        frame.setVisible(true);
    }

    public void llenarTabla(){
        String [] aux = {"Id", "fecha", "duracion", "sala"};
        DefaultTableModel visitas = new DefaultTableModel(null, aux);
        visitaTable.setDefaultEditor(Object.class, null);
        aux = new String[]{"Id", "DNI", "nombre", "apellidos"};
        DefaultTableModel monitores = new DefaultTableModel(null,aux);
        monitorTable.setDefaultEditor(Object.class, null);
        controller.llenarTabla(monitores, visitas);
        visitaTable.setModel(visitas);
        monitorTable.setModel(monitores);
    }

    private void colocarEscucha(){
        monitorTable.addMouseListener(controller);
        visitaTable.addMouseListener(controller);
        crearMonitor.addActionListener(controller);
        crearVisita.addActionListener(controller);
    }


    public JTable getMonitorTable() {
        return monitorTable;
    }

    public JTable getVisitaTable() {
        return visitaTable;
    }

    public static void main(String[] args) {
        omelcam934.views.Principal principal = new Principal();
        principal.iniciar(new PrincipalController());
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getCrearMonitor() {
        return crearMonitor;
    }

    public JButton getCrearVisita() {
        return crearVisita;
    }
}
