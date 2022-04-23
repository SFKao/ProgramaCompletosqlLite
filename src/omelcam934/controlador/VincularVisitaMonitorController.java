package omelcam934.controlador;

import omelcam934.dao.MonitorBBDD;
import omelcam934.dao.VisitasBBDD;
import omelcam934.modelo.Monitor;
import omelcam934.modelo.Visita;
import omelcam934.views.VincularVisitaMonitorVista;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VincularVisitaMonitorController implements ActionListener, MouseListener {

    private VincularVisitaMonitorVista vista;
    private Visita visita;
    private MonitorBBDD monitorBBDD;
    private VisitasBBDD visitasBBDD;

    public VincularVisitaMonitorController(Visita visita) {
        this();
        this.visita = visita;
    }

    public VincularVisitaMonitorController() {
        monitorBBDD = new MonitorBBDD();
        visitasBBDD = new VisitasBBDD();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.getButtonCancel()){
            vista.getFrame().dispose();
        }else if(e.getSource() == vista.getButtonOK()){
            asignarMonitor();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vista.getMonitores() && e.getClickCount() == 2){
            asignarMonitor();
        }
    }

    private void asignarMonitor(){
        int fila;
        if( (fila = vista.getMonitores().getSelectedRow()) != -1){
            Monitor m = monitorBBDD.listarMonitor((Integer) vista.getMonitores().getValueAt(fila,0));
            visitasBBDD.actualizarVisita(visita.getId(), visita.getFecha(), visita.getDuracion(), visita.getNumero_sala(), m);
            visita.setMonitor(m);
        }
        vista.getFrame().dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public VincularVisitaMonitorVista getVista() {
        return vista;
    }

    public void setVista(VincularVisitaMonitorVista vista) {
        this.vista = vista;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public void llenarTabla(DefaultTableModel monitores ){
        ArrayList<Monitor> monitors = (monitorBBDD.listarMonitores());

        for(Monitor m: monitors){
            Object aux[] = new Object[4];
            aux[0] = m.getId();
            aux[1] = m.getDni();
            aux[2] = m.getNombre();
            aux[3] = m.getApellidos();
            monitores.addRow(aux);
        }
    }


}
