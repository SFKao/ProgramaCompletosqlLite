package omelcam934.controlador;

import omelcam934.dao.MonitorBBDD;
import omelcam934.dao.VisitasBBDD;
import omelcam934.modelo.Monitor;
import omelcam934.modelo.Visita;
import omelcam934.views.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PrincipalController implements MouseListener, ActionListener {

    private MonitorBBDD monitorBBDD;
    private VisitasBBDD visitasBBDD;

    private Principal vista;

    private ArrayList<Monitor> monitors;
    private ArrayList<Visita> visitas;

    private boolean admin;

    public PrincipalController(Principal vista) {
        this();
        this.vista = vista;
    }

    public PrincipalController(boolean admin) {
        this();
        this.admin = admin;
    }

    public PrincipalController() {

        monitorBBDD = new MonitorBBDD();
        visitasBBDD = new VisitasBBDD();
        visitas = new ArrayList<>();
        monitors = new ArrayList<>();

    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        if(mouseEvent.getClickCount()== 2){
            if(mouseEvent.getSource() instanceof JTable){
                JTable table = (JTable) mouseEvent.getSource();
                int columna = table.getSelectedRow();
                if(table == vista.getMonitorTable()){
                    MonitorController monitorController = new MonitorController(monitorBBDD.listarMonitor((Integer) table.getValueAt(columna,0)),admin);
                    MonitorVista monitorVista = new MonitorVista();
                    monitorVista.iniciar(this.vista.getFrame(),monitorController);
                }else if(table == vista.getVisitaTable()){
                    VisitaController visitaController = new VisitaController(visitasBBDD.listarVisita((Integer) table.getValueAt(columna,0)),admin);
                    VisitaVista visitaVista = new VisitaVista();
                    visitaVista.iniciar(this.vista.getFrame(),visitaController );
                }
                vista.llenarTabla();
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO Abrir las ventanas de creación
        if(e.getSource()==vista.getCrearMonitor()){
            AnyadirMonitorController anyadirMonitorController = new AnyadirMonitorController();
            AnyadirMonitorVista anyadirMonitorVista = new AnyadirMonitorVista();
            anyadirMonitorVista.iniciar(vista.getFrame(),anyadirMonitorController);
            vista.llenarTabla();
        }else if(e.getSource() == vista.getCrearVisita()){
            if(monitors!=null) {
                AnyadirVisitaController anyadirVisitaController = new AnyadirVisitaController();
                AnyadirVisitaView anyadirVisitaView = new AnyadirVisitaView();
                anyadirVisitaView.iniciar(vista.getFrame(), anyadirVisitaController);
                vista.llenarTabla();
            }else{
                JOptionPane.showMessageDialog(vista.getFrame(), "Se necesita un monitor minimo antes de poder crear una visita");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public Principal getVista() {
        return vista;
    }

    public void setVista(Principal vista) {
        this.vista = vista;
    }

    public ArrayList<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(ArrayList<Monitor> monitors) {
        this.monitors = monitors;
    }

    public ArrayList<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(ArrayList<Visita> visitas) {
        this.visitas = visitas;
    }

    public void llenarTabla(DefaultTableModel monitores, DefaultTableModel visitas){
        this.visitas = visitasBBDD.listarVisitas();
        this.monitors = monitorBBDD.listarMonitores();

        if(this.visitas!=null)
            for (Visita v: this.visitas) {
                Object[] aux = new Object[4];
                aux[0] = v.getId();
                aux[1] = v.getFecha();
                aux[2] = v.getDuracion();
                aux[3] = v.getNumero_sala();
                visitas.addRow(aux);
            }
        if(this.monitors!=null)
            for(Monitor m: this.monitors){
                Object aux[] = new Object[4];
                aux[0] = m.getId();
                aux[1] = m.getDni();
                aux[2] = m.getNombre();
                aux[3] = m.getApellidos();
                monitores.addRow(aux);
            }
    }
}
