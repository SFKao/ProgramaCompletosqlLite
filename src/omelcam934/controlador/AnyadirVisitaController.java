package omelcam934.controlador;

import omelcam934.dao.VisitasBBDD;
import omelcam934.modelo.Visita;
import omelcam934.views.AnyadirVisitaView;
import omelcam934.views.VincularVisitaMonitorVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnyadirVisitaController implements ActionListener {

    private Visita visita;
    private VisitasBBDD visitasBBDD;
    private AnyadirVisitaView vista;

    public AnyadirVisitaController() {
        visita = new Visita();
        visitasBBDD = new VisitasBBDD();
    }

    public AnyadirVisitaView getVista() {
        return vista;
    }

    public void setVista(AnyadirVisitaView vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.getAnyadir()){
            if(visita.getMonitor()==null){
                vista.getVinculoLabel().setText("Se requiere vincular un monitor!!");
            }else{
                if(vista.getFecha().getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}")) {
                    try {
                        visita.setFecha(vista.getFecha().getText());
                        visita.setDuracion(Float.parseFloat(vista.getDuracion().getText()));
                        visita.setNumero_sala(Integer.parseInt(vista.getNumeroSala().getText()));
                        visitasBBDD.guardarVisita(visita);
                        vista.getFrame().dispose();
                    }catch (NumberFormatException formatException){
                        vista.getVinculoLabel().setText("El formato numerico no es correcto");
                    }
                }else {
                    vista.getVinculoLabel().setText("El formato de la fecha no es correcto");
                }
            }
        }else if(e.getSource()==vista.getVolver()){
            vista.getFrame().dispose();
        }else if(e.getSource()==vista.getVincularButton()){
            VincularVisitaMonitorController vincularVisitaMonitorController = new VincularVisitaMonitorController(visita);
            VincularVisitaMonitorVista vincularVisitaMonitorVista = new VincularVisitaMonitorVista();
            vincularVisitaMonitorVista.iniciar(vista.getFrame(), vincularVisitaMonitorController);
            vista.getVinculoLabel().setText("vinculo realizado");
        }
    }
}
