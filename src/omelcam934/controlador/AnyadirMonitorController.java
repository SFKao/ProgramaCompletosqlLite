package omelcam934.controlador;

import omelcam934.dao.MonitorBBDD;
import omelcam934.modelo.Monitor;
import omelcam934.views.AnyadirMonitorVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class AnyadirMonitorController implements ActionListener {

    private MonitorBBDD bbdd;
    private AnyadirMonitorVista vista;

    public AnyadirMonitorController() {
        bbdd = new MonitorBBDD();
    }

    public AnyadirMonitorVista getVista() {
        return vista;
    }

    public void setVista(AnyadirMonitorVista vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.getAnyadir()){
            if(vista.getDni().getText().matches("^[0-9]{8}[A-Za-z]$")) {
                if(vista.getNombre().getText() != ""){
                    Monitor m = new Monitor(vista.getDni().getText(), vista.getNombre().getText(), vista.getApellidos().getText());
                    try {
                        bbdd.guardarMonitor(m);
                        vista.getFrame().dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }else{
                vista.getDniInfo().setText("DNI INVALIDO");
            }
        }else if(e.getSource() == vista.getVolver()){
            vista.getFrame().dispose();
        }
    }
}
