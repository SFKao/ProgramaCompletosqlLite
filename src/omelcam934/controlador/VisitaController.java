package omelcam934.controlador;

import omelcam934.dao.VisitasBBDD;
import omelcam934.modelo.Visita;
import omelcam934.views.VincularVisitaMonitorVista;
import omelcam934.views.VisitaVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisitaController implements ActionListener {

    private Visita visita;

    private VisitasBBDD bbdd;

    private VisitaVista vista;

    public VisitaController(Visita visita, VisitaVista vista) {
        this();
        this.visita = visita;
        this.vista = vista;
    }

    public VisitaController(Visita visita) {
        this();
        this.visita = visita;
    }

    public VisitaController(VisitaVista vista) {
        this();
        this.vista = vista;
    }

    public VisitaController() {
        bbdd = new VisitasBBDD();
    }

    public Visita getVisita() {
        return visita;
    }

    public VisitaVista getVista() {
        return vista;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public void setVista(VisitaVista vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == vista.getEditarBox()){
            if(vista.getEditarBox().isSelected()){
                vista.getActualizar().setText("actualizar");
                vista.getActualizar().setEnabled(true);
                vista.getBorrar().setEnabled(true);
                vista.getCambiar().setEnabled(true);

                vista.getFecha().setEditable(true);
                vista.getNumeroSala().setEditable(true);
                vista.getDuracion().setEditable(true);

            }else{
                vista.getActualizar().setEnabled(false);
                vista.getBorrar().setEnabled(false);
                vista.getCambiar().setEnabled(false);

                vista.getFecha().setEditable(false);
                vista.getNumeroSala().setEditable(false);
                vista.getDuracion().setEditable(false);
            }
        } else if(actionEvent.getSource() == vista.getActualizar()){

            bbdd.actualizarVisita(visita.getId(), vista.getFecha().getText(),Float.valueOf(vista.getDuracion().getText()), Integer.valueOf(vista.getNumeroSala().getText()), visita.getMonitor());

            vista.getActualizar().setText("actualizado!");
            vista.getEditarBox().setSelected(false);
            vista.getActualizar().setEnabled(false);
            vista.getBorrar().setEnabled(false);
            vista.getCambiar().setEnabled(false);

            vista.getFecha().setEditable(false);
            vista.getNumeroSala().setEditable(false);
            vista.getDuracion().setEditable(false);
        } else if( actionEvent.getSource() == vista.getBorrar() ){
            bbdd.borrarVisita(visita.getId());
            vista.getFrame().dispose();
        } else if( actionEvent.getSource() == vista.getVolver() ) {
            vista.getFrame().dispose();
        } else  if(actionEvent.getSource() == vista.getCambiar()){
            VincularVisitaMonitorController vincularVisitaMonitorController = new VincularVisitaMonitorController(visita);
            VincularVisitaMonitorVista vincularVisitaMonitorVista = new VincularVisitaMonitorVista();
            vincularVisitaMonitorVista.iniciar(vista.getFrame(), vincularVisitaMonitorController);
            llenarDatos();


        }


    }

    public void llenarDatos() {

        vista.getId().setText(String.valueOf(Integer.valueOf(visita.getId())));
        vista.getFecha().setText(visita.getFecha());
        vista.getDuracion().setText(String.valueOf(visita.getDuracion()));
        vista.getNumeroSala().setText(String.valueOf(visita.getNumero_sala()));

        vista.getIdMonitor().setText(String.valueOf(visita.getMonitor().getId()));
        vista.getDni().setText(visita.getMonitor().getDni());
        vista.getNombre().setText(visita.getMonitor().getNombre());
        vista.getApellidos().setText(visita.getMonitor().getApellidos());
    }
}
