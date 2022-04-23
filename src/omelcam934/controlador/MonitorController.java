package omelcam934.controlador;

import omelcam934.dao.MonitorBBDD;
import omelcam934.modelo.Monitor;
import omelcam934.modelo.Visita;
import omelcam934.views.MonitorVista;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorController implements ActionListener {

    Monitor monitor = null;
    MonitorVista vista = null;
    MonitorBBDD bbdd = null;

    public MonitorController(MonitorVista vista) {
        this();
        this.vista = vista;

    }

    public MonitorController(Monitor monitor) {
        this();
        this.monitor = monitor;
    }

    public MonitorController() {
        bbdd = new MonitorBBDD();
    }

    public MonitorController(Monitor monitor, MonitorVista vista) {
        this();
        this.monitor = monitor;
        this.vista = vista;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public MonitorVista getVista() {
        return vista;
    }

    public void setVista(MonitorVista vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (vista.getActualizar().equals(source)) {
            try {
                bbdd.actualizarMonitor(monitor.getId(), vista.getDni().getText(), vista.getNombre().getText(), vista.getApellidos().getText());
                vista.getEditarBox().setSelected(false);
                vista.getActualizar().setText("actualizado!");
                vista.getActualizar().setEnabled(false);
                vista.getBorrar().setEnabled(false);
                vista.getDni().setEditable(false);
                vista.getNombre().setEditable(false);
                vista.getApellidos().setEditable(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (vista.getBorrar().equals(source)) {
            bbdd.borrarMonitor(monitor.getId());
            //vista.close();
            vista.getFrame().dispose();
        } else if (vista.getEditarBox().equals(source)) {
            if (vista.getEditarBox().isSelected()) {
                vista.getActualizar().setText("actualizar");
                vista.getActualizar().setEnabled(true);
                vista.getBorrar().setEnabled(true);
                vista.getDni().setEditable(true);
                vista.getNombre().setEditable(true);
                vista.getApellidos().setEditable(true);
            } else {
                vista.getActualizar().setEnabled(false);
                vista.getBorrar().setEnabled(false);
                vista.getDni().setEditable(false);
                vista.getNombre().setEditable(false);
                vista.getApellidos().setEditable(false);
            }
        } else if ( vista.getVolver().equals(source))
            vista.getFrame().dispose();

    }

    public void llenarTabla(DefaultTableModel tabla){
        for (Visita v: monitor.getVisitas()) {
            Object[] aux = new Object[4];
            aux[0] = v.getId();
            aux[1] = v.getFecha();
            aux[2] = v.getDuracion();
            aux[3] = v.getNumero_sala();
            tabla.addRow(aux);
        }
    }


    public void llenarDatos(){
        vista.setId(monitor.getId());
        vista.setDNI(monitor.getDni());
        vista.setNombre(monitor.getNombre());
        vista.setApellidos(monitor.getApellidos());
    }
}
